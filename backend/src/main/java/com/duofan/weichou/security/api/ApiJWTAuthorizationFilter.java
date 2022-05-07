package com.duofan.weichou.security.api;

import com.alibaba.fastjson.JSONObject;
import com.duofan.weichou.dto.response.Response;
import com.duofan.weichou.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

/**
 * Created by Arpit Khandelwal.
 */
public class ApiJWTAuthorizationFilter extends BasicAuthenticationFilter {
    public ApiJWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    // 拦截请求验证header是否有token
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {

            try {
                chain.doFilter(req, res);

            } catch (AccessDeniedException e) {

            } catch (Exception e) {
                res.setHeader("Content-Type", "application/json;charset=utf-8");
                PrintWriter writer = res.getWriter();
                writer.write(JSONObject.toJSONString(Response.error().setMessage("未知错误！")));
                writer.flush();
                writer.close();
            }
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }


    // 有token，这里就把token解析成用户信息，比如：用户名和角色
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();
            // Extract the UserName
            String user = claims.getSubject();
            // Extract the Roles
            ArrayList<String> roles = (ArrayList<String>) claims.get("roles");
            // Then convert Roles to GrantedAuthority Object for injecting
            ArrayList<GrantedAuthority> list = new ArrayList<>();
            if (roles != null) {
                for (String a : roles) {
                    GrantedAuthority g = new SimpleGrantedAuthority(a);
                    list.add(g);
                }
            }
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, list);
            }
            return null;
        }
        return null;
    }
}
