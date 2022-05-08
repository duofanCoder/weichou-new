package com.duofan.weichou.model.business;

import com.duofan.weichou.model.enums.AssetType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
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
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "biz_asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createTime;
    private Date updateTime;
    private String url;
    @Enumerated(EnumType.STRING)
    @OrderBy
    private AssetType assetType;
    @ManyToOne(targetEntity = CampaignDetail.class)
    @JoinColumn(name = "detail_id", referencedColumnName = "id")
    private CampaignDetail campaignDetail;
}
