package com.duofan.weichou.controller.v1.request;


import com.duofan.weichou.model.business.CampaignDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.duofan.weichou.model.business.Faq;
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
public class FaqRequest {
    private Long id;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String name;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String description;
    private String  question;
    private String  answer;
    private Long campaignDetailId;

}
