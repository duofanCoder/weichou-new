package com.duofan.weichou.dto.mapper.business;

import cn.hutool.core.util.ObjectUtil;
import com.duofan.weichou.dto.model.business.AssetDto;
import com.duofan.weichou.dto.model.business.FaqDto;
import com.duofan.weichou.model.business.Asset;
import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.business.Faq;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/4/4
 */
@Component
public class FaqMapper {
    @Autowired
    private ModelMapper modelMapper;

    public FaqDto toFaqDto(Faq model) {
        if (ObjectUtil.isEmpty(model)) {
            return null;
        }
        return modelMapper.map(model, FaqDto.class);
    }

    public List<FaqDto> toFaqDtoList(List<Faq> faqList) {
        if (ObjectUtil.isEmpty(faqList)) {
            return null;
        }
        LinkedList<FaqDto> list = new LinkedList<>();
        for (Faq asset : faqList) {
            list.add(toFaqDto(asset));
        }
        return list;
    }

    public List<Faq> toModelList(List<FaqDto> faqList, Long id) {
        if (ObjectUtil.isEmpty(faqList)) {
            return null;
        }
        LinkedList<Faq> list = new LinkedList<>();
        for (FaqDto asset : faqList) {
            list.add(toFaqModel(asset.setCampaignDetailId(id)));
        }
        return list;
    }

    public Faq toFaqModel(FaqDto faqDto) {
        return modelMapper.map(faqDto, Faq.class).setCampaignDetail(new CampaignDetail().setId(faqDto.getCampaignDetailId()));
    }
}
