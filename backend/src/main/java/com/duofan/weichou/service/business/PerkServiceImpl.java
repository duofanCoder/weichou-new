package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.PerkCondition;
import com.duofan.weichou.dto.mapper.business.PerkMapper;
import com.duofan.weichou.dto.model.business.PerkDto;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.business.Perk;
import com.duofan.weichou.repository.business.PerkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */
@Service
public class PerkServiceImpl implements PerkService {
    @Autowired
    private PerkRepository perkRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PerkMapper perkMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long[] primaryKey) {
        for (int i = 0; i < primaryKey.length; i++) {
            perkRepository.deleteById(primaryKey[i]);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 新增操作
    public PerkDto save(PerkDto dto) {
        Perk model = perkMapper.toPerkModel(dto);
        model.setCreateTime(new Date()).setUpdateTime(new Date()).setId(-1L);
        return modelMapper.map(perkRepository.save(model), PerkDto.class);
    }

    @Override
    public PerkDto getByPrimaryKey(Long primaryKey) {
        return modelMapper.map(perkRepository.findById(primaryKey), PerkDto.class);
    }

    @Override
    public PageDto<PerkDto> findPageByCondition(PerkCondition condition) {
        LinkedList<PerkDto> list = new LinkedList<>();
        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize());
        Page<Perk> modelPages = perkRepository.findByTitleContaining(condition.getName(), pageable);
        for (Perk campaign : modelPages.getContent()) {
            list.add(modelMapper.map(campaign, PerkDto.class));
        }
        return new PageDto<PerkDto>()
                .setCurrentPage(pageable.getPageNumber())
                .setTotalPage(modelPages.getTotalPages())
                .setData(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PerkDto update(PerkDto dto) {
        Perk model = perkRepository.findById(dto.getId()).orElseThrow(
                () -> new OwnerException("修改的部门不存在！")
        );
        //model.setName(dto.getName()).setDescription(dto.getDescription()).setUpdateTime(new Date());
        modelMapper.map(dto, model);
        return modelMapper.map(perkRepository.save(model), PerkDto.class);
    }

    @Override
    public void save(List<PerkDto> perkList, Long id) {
        if (perkList == null) {
            return;
        }
        for (PerkDto perkDto : perkList) {
            this.save(perkDto.setCampaignDetailId(id));
        }
    }

}
