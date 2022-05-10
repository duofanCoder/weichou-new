package com.duofan.weichou.dto.mapper.common;

import cn.hutool.core.date.DateUtil;
import com.duofan.weichou.controller.v1.request.common.UserSignupRequest;
import com.duofan.weichou.dto.model.common.UserDto;
import com.duofan.weichou.model.common.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Arpit Khandelwal.
 */
@Component
public class UserMapper {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    public UserDto toDto(User user) {
        if (user==null){
            return null;
        }
        return modelMapper.map(user, UserDto.class);
    }

    public User toModel(UserSignupRequest userDto) {
        User map = modelMapper.map(userDto, User.class);
        map.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        map.setBirth(DateUtil.parse("2000-01-01"))
                .setGender(true);
        return map;
    }

    public User toModel(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
