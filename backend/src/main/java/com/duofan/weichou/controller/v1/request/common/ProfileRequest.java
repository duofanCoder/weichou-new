package com.duofan.weichou.controller.v1.request.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileRequest {

    private String username;
    private String email;
    private String mobile;
    @Size(min = 1, max = 40)
    private String nickname;
    private Date birth;
    private String avatar;
    private String wechat;
    private String bilibili;
    private Boolean gender;

}