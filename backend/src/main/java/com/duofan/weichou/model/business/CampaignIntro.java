package com.duofan.weichou.model.business;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/28
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "biz_campaign_intro")
public class CampaignIntro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createTime;
    private Date updateTime;

    private String posterImg;
    private String title;
    private String description;
    private String location;
    @ManyToOne(targetEntity = Category.class)
    private Category category;
    private boolean isEye;
    private Double totalMoney;
    private Double currentMoney;
    private Date endTime;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "detail_id", referencedColumnName = "id")
    private CampaignDetail campaignDetail;
}
