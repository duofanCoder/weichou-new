package com.duofan.weichou.service.common;

import com.duofan.weichou.controller.v1.request.common.ProfileRequest;
import com.duofan.weichou.controller.v1.request.common.UserSignupRequest;
import com.duofan.weichou.model.common.User;
import com.duofan.weichou.model.enums.UserRole;
import com.duofan.weichou.dto.mapper.common.UserMapper;
import com.duofan.weichou.dto.model.common.UserDto;
import com.duofan.weichou.repository.common.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto signup(UserSignupRequest userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        if (user == null) {
            user = userMapper.toModel(userDto);
            user.setRole(UserRole.COMMON)
                    .setCreateTime(new Date())
                    .setUpdateTime(new Date());
            return userMapper.toDto(userRepository.save(user));
        } else {
            throw new RuntimeException("账号已存在！");
        }
    }

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    @Override
    @Transactional
    public UserDto findUserByUsername(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(email));
        if (user.isPresent()) {
            return userMapper.toDto(user.get());
        } else {
            return null;
        }
    }

    @Override
    public UserDto updateProfile(ProfileRequest profileRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = findUserByUsername(auth.getName());
        modelMapper.map(profileRequest, userDto);
        User user = userMapper.toModel(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    /**
     * Change Password
     *
     * @param userDto
     * @param newPassword
     * @return
     */
    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        User userModel = userRepository.findByUsername(userDto.getUsername());
        userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
        return userMapper.toDto(userRepository.save(userModel));
    }

}
