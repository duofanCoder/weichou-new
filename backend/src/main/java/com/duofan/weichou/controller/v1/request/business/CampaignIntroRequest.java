package com.duofan.weichou.controller.v1.request;


import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.business.Category;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    private Date createTime;
    private Date updateTime;
    private String posterImg;
    private String title;
    private String description;
    private String location;
    private Category category;
    private Double totalMoney;
    private Double currentMoney;
    private boolean isEye;
    private Date endTime;
    private Long categoryId;
}
