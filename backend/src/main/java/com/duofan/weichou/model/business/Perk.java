package com.duofan.weichou.model.business;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
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
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "biz_perk")
public class Perk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createTime;
    private Date updateTime;
    private String title;
    private String description;
    private int price;
    private String posterImg;
//    @ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
    @ManyToOne(targetEntity = CampaignDetail.class)
    @JoinColumn(name = "detail_id", referencedColumnName = "id")
    private CampaignDetail campaignDetail;
    private int orderNum;
}
