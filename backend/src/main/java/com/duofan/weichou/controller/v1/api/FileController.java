package com.duofan.weichou.controller.v1.api;

import com.duofan.weichou.dto.response.Response;
import com.duofan.weichou.service.common.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/11
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/file")
@Api(value = "weichou-application", description = "文件上传API操作接口")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件上传", notes = "文件上传", authorizations = {@Authorization(value = "apiKey")})
    @PostMapping(value = "/upload")
    public Response upload(MultipartFile[] files) {
        return Response.ok().setData(fileService.upload(files));
    }

}
