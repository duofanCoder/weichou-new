package com.duofan.weichou.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.duofan.weichou.dto.response.Response;
import com.duofan.weichou.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/6
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        PrintWriter writer = httpServletResponse.getWriter();
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        Optional<String> token = Optional.ofNullable(getToken(authentication));
        if (token.isPresent()) {
            httpServletResponse.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token.get());
            HashMap<String, Object> map = new HashMap<>();
            map.put("token",token.get());
            map.put("expiration", SecurityConstants.EXPIRATION_TIME);
            map.put("prefix", SecurityConstants.TOKEN_PREFIX);
            writer.write(JSONObject.toJSONString(Response.ok().setData(map)));
        } else {
            writer.write(JSONObject.toJSONString(Response.error()));
        }
        writer.flush();
        writer.close();
    }


    String getToken(Authentication auth) {
        if (auth.getPrincipal() != null) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            String login = user.getUsername();
            if (login != null && login.length() > 0) {
                Claims claims = Jwts.claims().setSubject(login);
                List<String> roles = new ArrayList<>();
                user.getAuthorities().stream().forEach(authority -> roles.add(authority.getAuthority()));
                claims.put("roles", roles);
                String token = Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                        .compact();
                return token;
            }
        }
        return null;
    }
}
