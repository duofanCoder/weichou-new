package com.duofan.weichou.model.business;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
@Table(name = "biz_campaign_detail")
public class CampaignDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createTime;
    private Date updateTime;


    @OneToMany(targetEntity = Asset.class)
    @JoinColumn(name = "campaign_detail_id", referencedColumnName = "id")
    private List<Asset> assetList;
    @OneToMany(targetEntity = Faq.class)
    @JoinColumn(name = "campaign_detail_id", referencedColumnName = "id")
    private List<Faq> faqList;
    @OneToMany(targetEntity = Perk.class)
    @JoinColumn(name = "campaign_detail_id", referencedColumnName = "id")
    private List<Perk> perkList;
    @OneToMany(targetEntity = Journal.class)
    @JoinColumn(name = "campaign_detail_id", referencedColumnName = "id")
    private List<Journal> journalList;
}
