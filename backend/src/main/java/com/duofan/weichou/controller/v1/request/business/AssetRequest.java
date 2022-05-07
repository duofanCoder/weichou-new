package com.duofan.weichou.controller.v1.request;


import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.enums.AssetType;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.duofan.weichou.model.business.Asset;
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
public class AssetRequest {
    private Long id;
    private String url;
    @JsonEnumDefaultValue
    private AssetType assetType;
    private Long campaignDetailId;
}
