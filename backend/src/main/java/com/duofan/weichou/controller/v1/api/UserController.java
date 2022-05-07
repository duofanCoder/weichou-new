package com.duofan.weichou.controller.v1.api;

import com.duofan.weichou.controller.v1.request.common.ProfileRequest;
import com.duofan.weichou.controller.v1.request.common.UserSignupRequest;
import com.duofan.weichou.dto.model.common.UserDto;
import com.duofan.weichou.dto.response.Response;
import com.duofan.weichou.service.common.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/api/v1/user")
@Api(value = "application")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("Login")
    @PostMapping("/login")
    public void fakeLogin(@RequestBody @Valid LoginRequest loginRequest) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @ApiOperation("Logout")
    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public void fakeLogout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class LoginRequest {
        @NotNull(message = "{constraints.NotEmpty.message}")
        private String username;
        @NotNull(message = "{constraints.NotEmpty.message}")
        private String password;
    }


    @PostMapping(value = "/signup")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response signup(@RequestBody UserSignupRequest userSignupRequest) {
        return Response.ok().setData(userService.signup(userSignupRequest));
    }


    @GetMapping(value = "/profile")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("isAuthenticated()")
    public Response getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Response.ok().setData(userService.findUserByUsername(auth.getName()));
    }


    @PostMapping(value = "/profile")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response updateProfile(@RequestBody @Valid ProfileRequest profileRequest) {
        return Response.ok().setData(userService.updateProfile(profileRequest));
    }


    @Data
    @Accessors(chain = true)
    private static class PasswordFormCommand {
        private String password;
    }

    @PostMapping(value = "/password")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response changePassword(@RequestBody PasswordFormCommand passwordFormCommand) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.findUserByUsername(auth.getName());
        userService.changePassword(userDto, passwordFormCommand.getPassword());
        SecurityContextHolder.getContext().setAuthentication(null);
        return Response.ok();
    }
}
