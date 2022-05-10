package com.duofan.weichou.model.business;

import com.duofan.weichou.model.common.User;
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
    @ToString.Exclude
    @ManyToOne(targetEntity = Category.class)
    private Category category;
    private boolean isEye;
    private Double totalMoney;
    private Double currentMoney;
    private Date endTime;
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id", referencedColumnName = "id",updatable = false)
    private CampaignDetail campaignDetail;

    @ToString.Exclude
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;
}
