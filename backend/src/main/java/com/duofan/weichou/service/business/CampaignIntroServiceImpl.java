package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.CampaignIntroCondition;
import com.duofan.weichou.dto.model.business.CampaignIntroDto;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.business.CampaignIntro;
import com.duofan.weichou.repository.business.CampaignIntroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */
@Service
public class CampaignIntroServiceImpl implements CampaignIntroService {
    @Autowired
    private CampaignIntroRepository campaignIntroRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long[] primaryKey) {
        for (int i = 0; i < primaryKey.length; i++) {
            campaignIntroRepository.deleteById(primaryKey[i]);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 新增操作
    public CampaignIntroDto save(CampaignIntroDto dto) {
        CampaignIntro model = modelMapper.map(dto, CampaignIntro.class);
        model.setCreateTime(new Date()).setUpdateTime(new Date());
        model.setCampaignDetail(new CampaignDetail());
        return modelMapper.map(campaignIntroRepository.save(model), CampaignIntroDto.class);
    }

    @Override
    public CampaignIntroDto getByPrimaryKey(Long primaryKey) {
        return modelMapper.map(campaignIntroRepository.findById(primaryKey).get(), CampaignIntroDto.class);
    }

    @Override
    public PageDto<CampaignIntroDto> findPageByCondition(CampaignIntroCondition condition) {
//        LinkedList<CampaignIntroDto> list = new LinkedList<>();
//        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize());
//        Page<CampaignIntro> modelPages = campaignIntroRepository.findByNameContaining(condition.getName(), pageable);
//        for (CampaignIntro campaign : modelPages.getContent()) {
//            list.add(modelMapper.map(campaign, CampaignIntroDto.class));
//        }
//        return new PageDto<CampaignIntroDto>()
//                .setCurrentPage(pageable.getPageNumber())
//                .setTotalPage(modelPages.getTotalPages())
//                .setData(list);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CampaignIntroDto update(CampaignIntroDto dto) {
        CampaignIntro model = campaignIntroRepository.findById(dto.getId()).orElseThrow(
                () -> new OwnerException("修改的部门不存在！")
        );
        //model.setName(dto.getName()).setDescription(dto.getDescription()).setUpdateTime(new Date());
        modelMapper.map(dto, model);
        return modelMapper.map(campaignIntroRepository.save(model), CampaignIntroDto.class);
    }

}
