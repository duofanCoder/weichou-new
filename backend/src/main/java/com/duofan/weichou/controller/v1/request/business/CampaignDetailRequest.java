package com.duofan.weichou.controller.v1.request;


import com.duofan.weichou.model.business.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

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
public class CampaignDetailRequest {
    private Long id;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String name;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String description;

    private List<Asset> assetList;
    private List<Faq> faqList;
    private List<Perk> perkList;
    private List<Journal> journalList;

    private Long[] assetIds;
    private Long[] faqIds;
    private Long[] perkIds;
    private Long[] journalIds;
    private CampaignDetail campaignDetail;
    private Long campaignDetailId;
}
