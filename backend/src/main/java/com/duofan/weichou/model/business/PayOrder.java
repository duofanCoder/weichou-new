package com.duofan.weichou.model.business;

import com.duofan.weichou.model.common.User;
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
@Table(name = "biz_pay_order")
public class PayOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createTime;
    private Date updateTime;

    @ManyToOne(optional = false)
    private User owner;

    @ToString.Exclude
    @ManyToOne(targetEntity = Perk.class)
    private Perk perk;
    @ToString.Exclude
    @ManyToOne(targetEntity = CampaignDetail.class)
    @JoinColumn(name = "detail_id", referencedColumnName = "id")
    private CampaignDetail  campaignDetail;


}
