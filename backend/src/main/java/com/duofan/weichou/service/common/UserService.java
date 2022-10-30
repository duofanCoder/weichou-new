package com.duofan.weichou.service.common;

import com.duofan.weichou.controller.v1.condition.common.UserCondition;
import com.duofan.weichou.controller.v1.request.common.ProfileRequest;
import com.duofan.weichou.controller.v1.request.common.UserSignupRequest;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.dto.model.common.UserDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Arpit Khandelwal.
 */
public interface UserService {
    /**
     * Register a new user
     *
     * @param userDto
     * @return
     */
    UserDto signup(UserSignupRequest userDto);

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    UserDto findUserByUsername(String email);

    /**
     * Update profile of the user
     *
     * @param userDto
     * @return
     */
    UserDto updateProfile(ProfileRequest userDto);

    /**
     * Update password
     *
     * @param newPassword
     * @return
     */
    UserDto changePassword(UserDto userDto, String newPassword);

    PageDto<UserDto> findPageByCondition(UserCondition condition);

    void removeByPrimaryKey(Long[] primaryKeys);

    @Transactional(rollbackFor = Exception.class)
    UserDto update(UserDto dto);
}
