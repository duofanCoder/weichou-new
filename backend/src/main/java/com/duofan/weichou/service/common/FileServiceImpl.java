package com.duofan.weichou.service.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import com.duofan.weichou.dto.model.common.FileDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.common.User;
import com.duofan.weichou.repository.common.FileRepository;
import com.duofan.weichou.repository.common.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author DuoFan
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Value("${user.file.rootPath}")
    private String rootPath;

    @Value("${user.file.url}")
    private String prefixPath;
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FileDto> upload(MultipartFile[] files) {
        if (null == files || files.length == 0) {
            throw new OwnerException("请至少选择一个图片！");
        }
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(email);

        LinkedList<FileDto> boList = new LinkedList<>();
        for (MultipartFile file : files) {
            try {
                String type = FileTypeUtil.getType(file.getInputStream());
                log.info(type + "文件类型:");
                if (!(type.equals(ImgUtil.IMAGE_TYPE_JPG) | type.equals(ImgUtil.IMAGE_TYPE_PNG) | type.equals(ImgUtil.IMAGE_TYPE_BMP)
                        | type.equals(ImgUtil.IMAGE_TYPE_JPEG) | type.equals(ImgUtil.IMAGE_TYPE_PSD))) {
                    throw new OwnerException("图片格式不符合！");
                }
            } catch (IORuntimeException | IOException e) {
                throw new OwnerException("非图片文件，请上传图片！");
            }
            try {
                com.duofan.weichou.model.common.File bizFile = getImageInfo(file);
                File dest = new File(rootPath + bizFile.getFilePath());
                try {
                    file.transferTo(dest);
                } catch (IOException e) {
                    log.error(e.getMessage());
                    throw new OwnerException("文件保存失败，请上传图片！");
                }
                fileRepository.save(bizFile);
                bizFile.setCreateTime(new Date()).setUpdateTime(new Date());
                boList.add(modelMapper.map(bizFile, FileDto.class));
                bizFile.setOwner(user);
            } catch (IOException ignored) {
                throw new OwnerException("图片信息获取异常，请上传图片！");
            }
        }
        return boList;
    }

    private com.duofan.weichou.model.common.File getImageInfo(MultipartFile file) throws IOException {
        BufferedInputStream in = new BufferedInputStream(file.getInputStream());
        //字节流转图片对象
        Image bi = ImageIO.read(in);
        String suffix = "." + FileUtil.getSuffix(file.getOriginalFilename());
        String fileName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
        //获取默认图像的高度，宽度
        return new com.duofan.weichou.model.common.File()
                .setWidth(bi.getWidth(null))
                .setHeight(bi.getHeight(null))
                .setSize(file.getSize())
                .setSuffix(suffix)
                .setFilePath("img/" + fileName + suffix)
                .setFullFilePath(prefixPath + "img/" + (fileName + suffix))
                .setOriginalFileName(file.getOriginalFilename());
    }

    @Override
    public com.duofan.weichou.model.common.File saveFile(MultipartFile file) {
        com.duofan.weichou.model.common.File bizFile = null;
        try {
            if (file.isEmpty()) {
                throw new OwnerException("文件不能为空！");
            }
            bizFile = getFileInfo(file);
            File dest = new File(rootPath + bizFile.getFilePath());
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return fileRepository.save(bizFile);
    }

    private com.duofan.weichou.model.common.File getFileInfo(MultipartFile file) throws IOException {
        //字节流转图片对象
        String suffix = "." + FileUtil.getSuffix(file.getOriginalFilename());
        String fileName = String.valueOf(UUID.randomUUID());
        //获取默认图像的高度，宽度
        return new com.duofan.weichou.model.common.File()
                .setCreateTime(new Date())
                .setUpdateTime(new Date())
                .setSize(file.getSize())
                .setSuffix(suffix)
                .setFilePath("file/" + fileName + suffix)
                .setFullFilePath(prefixPath + (fileName + suffix))
                .setOriginalFileName(file.getOriginalFilename());
    }
}