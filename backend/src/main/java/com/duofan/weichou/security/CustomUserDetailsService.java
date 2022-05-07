package com.duofan.weichou.security;

import com.duofan.weichou.dto.model.common.UserDto;
import com.duofan.weichou.model.enums.UserRole;
import com.duofan.weichou.service.common.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Arpit Khandelwal.
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    // 查看是否有改用户，没有则抛出异常
    // 如果有则传给框架，也就是返回UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = null;
        try {
            userDto = userService.findUserByUsername(username);
            List<GrantedAuthority> authorities = getUserAuthority(userDto.getRole());
            return buildUserForAuthentication(userDto, authorities);
        } catch (RuntimeException e) {
            log.info(e.getMessage());
            throw new UsernameNotFoundException("用户名 " + username + " 不存在。");
        }
    }

    private List<GrantedAuthority> getUserAuthority(UserRole role) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.toString()));
        return new ArrayList<GrantedAuthority>(roles);
    }

    private UserDetails buildUserForAuthentication(UserDto user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


}
