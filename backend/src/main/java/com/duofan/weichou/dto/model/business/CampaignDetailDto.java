package com.duofan.weichou.dto.model.business;


import com.duofan.weichou.dto.model.common.UserDto;
import com.duofan.weichou.model.business.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
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
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignDetailDto {
    private Long id;
    private String name;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date updateTime;


    private List<AssetDto> assetList;
    private List<FaqDto> faqList;
    private List<PerkDto> perkList;
    private List<JournalDto> journalList;

    private Long[] assetIds;
    private Long[] faqIds;
    private Long[] perkIds;
    private Long[] journalIds;
    private CampaignIntroDto campaignIntro;
    private Long campaignIntroId;

    private UserDto owner;

    private JournalDto story;
    private int supportCount;
}
