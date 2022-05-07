package com.duofan.weichou.service.common;

import com.duofan.weichou.dto.model.common.FileDto;
import com.duofan.weichou.model.common.File;
import com.duofan.weichou.dto.model.common.FileDto;
import com.duofan.weichou.model.common.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/11
 */
public interface FileService {

    List<FileDto> upload(MultipartFile[] file);

    File saveFile(MultipartFile file);
}
