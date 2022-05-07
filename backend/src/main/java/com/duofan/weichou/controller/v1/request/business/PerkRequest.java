package com.duofan.weichou.controller.v1.request;


import com.duofan.weichou.model.business.CampaignDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.duofan.weichou.model.business.Perk;
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
public class PerkRequest {
    private Long id;
    private String title;
    private String description;
    private int price;
    private String posterImg;
    private int orderNum;

    private CampaignDetail campaignDetail;
    private Long campaignDetailId;
}
