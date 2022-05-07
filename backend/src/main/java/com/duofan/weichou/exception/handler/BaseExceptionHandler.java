package com.duofan.weichou.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.duofan.weichou.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
//@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    private static void logFormat(String exMessage, String uri) {
        log.error("" + "[错误日志]" + "[================================================================]" + "[异常信息] - [{}]" + "[请求地址] - [{}]" + "[================================================================]", exMessage, uri);
    }

    private static void logFormat(String exMessage, String uri, String method) {
        log.error("" + "[错误日志]" + "[================================================================]" + "[异常信息] - [{}]" + "[请求地址] - [{}] - [{}]" + "[================================================================]", exMessage, uri, method);
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler
    @ResponseBody
    public Response<?> handleException(NullPointerException ex, HttpServletRequest request) {
        logFormat(ex.getLocalizedMessage(), request.getRequestURI(), request.getMethod());
        return Response.error();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Response> jsonErrorHandler(HttpServletRequest request, Exception ex) {
        HttpStatus httpStatus = HttpStatus.OK;
        log.error("[错误日志] - {} - {}", request.getRequestURI(), ex);
        if (ex instanceof IllegalArgumentException) {
            return new ResponseEntity(Response.error().setMessage(ex.getMessage()), httpStatus);
        } else if (ex instanceof MultipartException) {
            return new ResponseEntity(Response.error().setMessage("文件上传失败！"), httpStatus);
        } else if (ex instanceof RuntimeException) {
            return new ResponseEntity(Response.error().setMessage(ex.getMessage()), httpStatus);
        }
        return new ResponseEntity(Response.error().setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), httpStatus);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        if (ex instanceof MethodArgumentNotValidException) {
            logFormat(ex.getLocalizedMessage(), uri);
            return new ResponseEntity(Response.error().setMessage(ex.getLocalizedMessage()), HttpStatus.OK);
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseEntity(Response.error().setMessage(String.format("%s 请求方式 %s 不存在", uri, ((HttpRequestMethodNotSupportedException) ex).getMethod())), HttpStatus.OK);
        } else if (ex instanceof MethodArgumentTypeMismatchException) {
            logger.error("[错误日志]参数转换失败，方法：" + Objects.requireNonNull(((MethodArgumentTypeMismatchException) ex).getParameter().getMethod()).getName() + "，参数：" + ((MethodArgumentTypeMismatchException) ex).getName() + ",信息：" + ex.getLocalizedMessage());
            if (ex.getCause() instanceof ConversionFailedException && ex.getCause().getCause() instanceof IllegalArgumentException) {
                return new ResponseEntity(Response.error().setMessage(ex.getCause().getCause().getLocalizedMessage()), HttpStatus.OK);
            }
            return new ResponseEntity(Response.error().setMessage("表单填写错误"), HttpStatus.OK);
        } else if (ex instanceof HttpMessageNotReadableException) {
            logger.error("[错误日志]参数转换失败" + ex.getLocalizedMessage());
            if (ex.getCause() instanceof InvalidFormatException) {
                return new ResponseEntity(Response.error().setMessage("字段类型映射错误 " + ex.getCause().getMessage()), HttpStatus.OK);
            }
        } else if (ex instanceof NoHandlerFoundException) {
            logger.error("[错误日志]请求地址错误" + ex.getLocalizedMessage());
            return new ResponseEntity(Response.error().setMessage("请求地址错误 " + ex.getMessage()), HttpStatus.OK);
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}