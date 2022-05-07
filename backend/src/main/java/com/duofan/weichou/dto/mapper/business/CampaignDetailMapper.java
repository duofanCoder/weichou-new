package com.duofan.weichou.dto.mapper.business;

import cn.hutool.core.util.ObjectUtil;
import com.duofan.weichou.dto.model.business.CampaignDetailDto;
import com.duofan.weichou.model.business.Asset;
import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.business.CampaignIntro;
import com.duofan.weichou.model.business.Faq;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/4/4
 */
@Component
public class CampaignDetailMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private FaqMapper faqMapper;
    @Autowired
    private JournalMapper journalMapper;
    @Autowired
    private PerkMapper perkMapper;

    public CampaignDetailDto toCampaignDetailDto(CampaignDetail model) {
        if (ObjectUtil.isEmpty(model)) {
            return null;
        }
        return new CampaignDetailDto()
                .setId(model.getId())
//                .setCampaignIntroId(model.getCampaignIntro().getId())
                .setAssetList(assetMapper.toAssetDtoList(model.getAssetList()))
                .setFaqList(faqMapper.toFaqDtoList(model.getFaqList()))
                .setJournalList(journalMapper.toJournalDtoList(model.getJournalList()))
                .setPerkList(perkMapper.toPerkDtoList(model.getPerkList()));
    }

    public CampaignDetail toModel(CampaignDetailDto dto) {
        CampaignDetail campaignDetail = new CampaignDetail();
        campaignDetail.setId(dto.getId())
                .setAssetList(assetMapper.toModelList(dto.getAssetList(), dto.getId()))
                .setFaqList(faqMapper.toModelList(dto.getFaqList(), dto.getId()))
                .setJournalList(journalMapper.toModelList(dto.getJournalList(), dto.getId()))
                .setPerkList(perkMapper.toModelList(dto.getPerkList(), dto.getId()));
        return campaignDetail;
    }
}
