package com.duofan.weichou.controller.v1.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayOrderRequest {
    private Long id;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String name;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String description;

    private Long perkId;
    private  Long campaignDetailId;

}
