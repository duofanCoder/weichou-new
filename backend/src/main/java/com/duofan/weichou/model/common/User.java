package com.duofan.weichou.model.common;

import com.duofan.weichou.model.business.CampaignIntro;
import com.duofan.weichou.model.business.PayOrder;
import com.duofan.weichou.model.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Arpit Khandelwal.
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "sys_user",
        indexes = @Index(
                name = "idx_user_username",
                columnList = "username",
                unique = true)
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createTime;
    private Date updateTime;

    private String email;
    private String password;
    private String username;
    private String nickname;
    private Date birth;
    private boolean gender;
    private String avatar;
    private String bilibili;
    private String wechat;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(targetEntity = CampaignIntro.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private List<CampaignIntro> campaignIntroList;

    @OneToMany(targetEntity = PayOrder.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id",updatable = false,insertable = false,nullable = false)
    private List<PayOrder> payOrderList;

}
