package com.duofan.weichou.controller.v1.request;


import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.business.Category;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
public class CampaignIntroRequest {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    @Temporal(TemporalType.DATE)
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    @Temporal(TemporalType.DATE)
    private Date updateTime;
    private String posterImg;
    private String title;
    private String description;
    private String location;
    private Double totalMoney;
    private Double currentMoney;
    private boolean isEye;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    @Temporal(TemporalType.DATE)
    private Date endTime;
    private Long categoryId;
    private Long CampaignDetailId;
}
