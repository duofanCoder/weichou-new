package com.duofan.weichou;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.duofan.weichou.controller.v1.request.JournalRequest;
import com.duofan.weichou.dto.model.business.*;
import com.duofan.weichou.model.business.*;
import com.duofan.weichou.model.enums.AssetType;
import com.duofan.weichou.model.enums.JournalType;
import com.duofan.weichou.service.business.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class WeiChouApplicationTests {


    @Autowired
    private CampaignIntroService campaignIntroService;

    @Autowired
    private CampaignDetailService campaignDetailService;

    @Test
    public void InsertCampaignIntro() {
        CampaignIntroDto campaignIntroDto = new CampaignIntroDto()
                .setTitle("Sneapon Unisex Comfortable Sandals")
                .setDescription("A fusion of traditional Japanese geta sandals and modern sneakers.")
                .setCategory(new Category().setId(2L))
                .setEndTime(DateUtil.parse("2030-01-01"))
                .setPosterImg("http://localhost:8080/file/img/20220506233531626.jpg")
                .setLocation("中国 南京");
        System.out.println(campaignIntroService.save(campaignIntroDto));
    }

    //    测试detail
    //    mapperby 属性是否可以被动维护asset、faq、journal
    @Test
    public void InsertCampaignDetail() {
        CampaignDetailDto campaignDetailDto = new CampaignDetailDto()
                .setCampaignIntro(new CampaignIntroDto().setId(2L));
        System.out.println(campaignDetailService.save(campaignDetailDto));
    }

    @Test
    @Transactional
    public void selectCampaign() {
        CampaignIntroDto byPrimaryKey = campaignIntroService.getByPrimaryKey(2L);
        CampaignDetailDto byPrimaryKey1 = campaignDetailService.getByPrimaryKey(2L);
    }


    @Autowired
    private AssetService assetService;
    @Autowired
    private FaqService faqService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private PerkService perkService;


    @Test
    public void insertCampaignAdd() {
        CampaignDetailDto byPrimaryKey1 = campaignDetailService.getByPrimaryKey(2L);
        if (byPrimaryKey1 == null) {
            byPrimaryKey1 = new CampaignDetailDto().setUpdateTime(new Date()).setCreateTime(new Date());
        } else {
            LinkedList<AssetDto> assetList = new LinkedList<>();
            LinkedList<PerkDto> perkList = new LinkedList<>();
            LinkedList<FaqDto> faqList = new LinkedList<>();

            assetList.add(new AssetDto().setAssetType(AssetType.IMAGE)
                    .setUrl("这是地址").setCampaignDetailId(2L));
            assetList.add(new AssetDto().setAssetType(AssetType.IMAGE)
                    .setUrl("这是地址2").setCampaignDetailId(2L));
            assetList.add(new AssetDto().setAssetType(AssetType.VIDEO)
                    .setUrl("这是地址3").setCampaignDetailId(2L));
            faqList.add(new FaqDto().setAnswer("回答1").setQuestion("问题1").setCampaignDetailId(2L));
            faqList.add(new FaqDto().setAnswer("回答2").setQuestion("问题2").setCampaignDetailId(2L));
            faqList.add(new FaqDto().setAnswer("回答3").setQuestion("问题3").setCampaignDetailId(2L));

            byPrimaryKey1.setAssetList(assetList).setFaqList(faqList);
        }
        System.out.println(campaignDetailService.save(byPrimaryKey1));
    }

    @Test
    public void contextLoads() {
        insertCampaignAdd();
    }

}
