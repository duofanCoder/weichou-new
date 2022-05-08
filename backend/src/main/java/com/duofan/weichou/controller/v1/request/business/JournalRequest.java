package com.duofan.weichou.controller.v1.request;


import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.enums.JournalType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.duofan.weichou.model.business.Journal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class JournalRequest {
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private JournalType journalType;
    private String content;
    private Long campaignDetailId;
    private String posterImg;
}
