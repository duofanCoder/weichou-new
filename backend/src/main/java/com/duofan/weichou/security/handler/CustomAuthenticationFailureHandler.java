package com.duofan.weichou.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.duofan.weichou.dto.response.Response;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/6
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Response result = null;
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        if (e instanceof BadCredentialsException) {
            result = Response.error().setMessage("账号或密码错误！");
        } else {
            result = Response.error().setMessage("登录失败！");
        }
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSONObject.toJSONString(result));
        writer.flush();
        writer.close();
    }
}
