package com.duofan.weichou.service.common;

import com.duofan.weichou.controller.v1.condition.common.UserCondition;
import com.duofan.weichou.controller.v1.request.common.ProfileRequest;
import com.duofan.weichou.controller.v1.request.common.UserSignupRequest;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.common.User;
import com.duofan.weichou.model.enums.UserRole;
import com.duofan.weichou.dto.mapper.common.UserMapper;
import com.duofan.weichou.dto.model.common.UserDto;
import com.duofan.weichou.repository.common.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.Predicate;


import java.util.*;


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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageDto<UserDto> findPageByCondition(UserCondition condition) {
        LinkedList<UserDto> list = new LinkedList<>();
        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize(), Sort.by("createTime"));
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!condition.getName().isEmpty()) {
                predicateList.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + condition.getName() + "%"));
            }
            return criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()])).getRestriction();
        };
        Page<User> modelPages = userRepository.findAll(spec, pageable);
        for (User campaign : modelPages.getContent()) {
            list.add(userMapper.toUserDto(campaign));
        }
        return new PageDto<UserDto>()
                .setCurrentPage(pageable.getPageNumber())
                .setTotalPage(modelPages.getTotalPages())
                .setData(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long[] primaryKey) {
        for (int i = 0; i < primaryKey.length; i++) {
            userRepository.deleteById(primaryKey[i]);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto update(UserDto dto) {
        User model = userRepository.findById(dto.getId()).orElseThrow(
                () -> new OwnerException("修改的用户不存在！")
        );


        model.setId(dto.getId())
                .setId(dto.getId())
                .setMobile(dto.getMobile())
                .setNickname(dto.getNickname())
                .setUsername(dto.getUsername())
                .setBilibili(dto.getBilibili())
                .setWechat(dto.getWechat())
                .setGender(dto.isGender())
                .setUpdateTime(dto.getUpdateTime());

        return modelMapper.map(userRepository.save(model), UserDto.class);
    }



}
