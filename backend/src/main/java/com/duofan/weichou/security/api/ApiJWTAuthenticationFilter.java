package com.duofan.weichou.security.api;

import com.duofan.weichou.model.common.User;
import com.duofan.weichou.security.handler.CustomAuthenticationFailureHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.duofan.weichou.security.handler.CustomAuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Arpit Khandelwal.
 */
@Slf4j
public class ApiJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public ApiJWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        // 这个参数由调用的框架传来，负责验证，验证失败会抛出异常
        this.authenticationManager = authenticationManager;
        // 框架默认是/login，这里重新匹配登录接口
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/v1/user/login", "POST"));
        this.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        this.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        User user = null;
        try {
            user = new ObjectMapper().readValue(req.getInputStream(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 传给框架做验证
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
    }
}
