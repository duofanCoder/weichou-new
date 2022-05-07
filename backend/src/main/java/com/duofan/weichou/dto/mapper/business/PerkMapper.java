package com.duofan.weichou.dto.mapper.business;

import cn.hutool.core.util.ObjectUtil;
import com.duofan.weichou.dto.model.business.AssetDto;
import com.duofan.weichou.dto.model.business.JournalDto;
import com.duofan.weichou.dto.model.business.PerkDto;
import com.duofan.weichou.model.business.Asset;
import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.business.Journal;
import com.duofan.weichou.model.business.Perk;
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
public class PerkMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PerkDto toPerkDto(Perk model) {
        if (ObjectUtil.isEmpty(model)) {
            return null;
        }
        return modelMapper.map(model, PerkDto.class);
    }

    public List<PerkDto> toPerkDtoList(List<Perk> perkList) {
        if (ObjectUtil.isEmpty(perkList)) {
            return null;
        }
        LinkedList<PerkDto> list = new LinkedList<>();
        for (Perk asset : perkList) {
            list.add(toPerkDto(asset));
        }
        return list;
    }

    public List<Perk> toModelList(List<PerkDto> perkList, Long id) {
        if (ObjectUtil.isEmpty(perkList)) {
            return null;
        }
        LinkedList<Perk> list = new LinkedList<>();
        for (PerkDto asset : perkList) {
            list.add(toPerkModel(asset.setCampaignDetailId(id)));
        }
        return list;
    }

    public Perk toPerkModel(PerkDto perk) {
        return modelMapper.map(perk, Perk.class).setCampaignDetail(new CampaignDetail().setId(perk.getCampaignDetailId()));
    }
}
