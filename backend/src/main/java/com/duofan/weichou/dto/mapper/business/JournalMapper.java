package com.duofan.weichou.dto.mapper.business;

import cn.hutool.core.util.ObjectUtil;
import com.duofan.weichou.dto.model.business.AssetDto;
import com.duofan.weichou.dto.model.business.FaqDto;
import com.duofan.weichou.dto.model.business.JournalDto;
import com.duofan.weichou.model.business.Asset;
import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.business.Faq;
import com.duofan.weichou.model.business.Journal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
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
public class JournalMapper {
    @Autowired
    private ModelMapper modelMapper;

    public JournalDto toJournalDto(Journal model) {
        if (ObjectUtil.isEmpty(model)) {
            return null;
        }
        return modelMapper.map(model, JournalDto.class);
    }

    public List<JournalDto> toJournalDtoList(List<Journal> journalList) {
        if (ObjectUtil.isEmpty(journalList)) {
            return null;
        }
        LinkedList<JournalDto> list = new LinkedList<>();
        for (Journal asset : journalList) {
            list.add(toJournalDto(asset));
        }
        return list;
    }

    public List<Journal> toModelList(List<JournalDto> journalList, Long id) {
        if (ObjectUtil.isEmpty(journalList)) {
            return null;
        }
        LinkedList<Journal> list = new LinkedList<>();
        for (JournalDto asset : journalList) {
            list.add(toJournalModel(asset.setCampaignDetailId(id)));
        }
        return list;

    }

    public Journal toJournalModel(JournalDto journalDto) {
        return modelMapper.map(journalDto, Journal.class).setCampaignDetail(new CampaignDetail().setId(journalDto.getCampaignDetailId()));

    }
}
