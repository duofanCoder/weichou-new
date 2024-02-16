package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.CampaignDetailCondition;
import com.duofan.weichou.dto.mapper.business.CampaignDetailMapper;
import com.duofan.weichou.dto.model.business.*;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.business.Perk;
import com.duofan.weichou.repository.business.CampaignDetailRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.aspectj.weaver.ast.Var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.Optional;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */
@Service
public class CampaignDetailServiceImpl implements CampaignDetailService {
    @Autowired
    private CampaignDetailRepository campaignDetailRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CampaignDetailMapper campaignDetailMapper;

    @Autowired
    private CampaignIntroService campaignIntroService;

    @Autowired
    private FaqService faqService;

    @Autowired
    private JournalService journalService;

    @Autowired
    private PerkService perkService;

    @Autowired
    private AssetService assetService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long[] primaryKey) {
        for (int i = 0; i < primaryKey.length; i++) {
            campaignDetailRepository.deleteById(primaryKey[i]);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 新增操作
    public CampaignDetailDto save(CampaignDetailDto dto) {
        CampaignDetail model = campaignDetailMapper.toModel(dto);
        model.setCreateTime(new Date()).setUpdateTime(new Date());
        model = campaignDetailRepository.save(model);
        assetService.save(dto.getAssetList(), model.getId());
        faqService.save(dto.getFaqList(), model.getId());
        journalService.save(dto.getJournalList(), model.getId());
        perkService.save(dto.getPerkList(), model.getId());
        return modelMapper.map(model, CampaignDetailDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public CampaignDetailDto getByPrimaryKey(Long primaryKey) {
        Optional<CampaignDetail> optional = campaignDetailRepository.findById(primaryKey);
        if (optional.isPresent()) {
            return campaignDetailMapper.toCampaignDetailDto(optional.get());
        } else {
            return null;
        }
    }

    @Override
    public PageDto<CampaignDetailDto> findPageByCondition(CampaignDetailCondition condition) {
//        LinkedList<CampaignDetailDto> list = new LinkedList<>();
//        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize());
//        Page<CampaignDetail> modelPages = campaignDetailRepository.findByNameContaining(condition.getName(), pageable);
//        for (CampaignDetail campaign : modelPages.getContent()) {
//            list.add(modelMapper.map(campaign, CampaignDetailDto.class));
//        }
//        return new PageDto<CampaignDetailDto>()
//                .setCurrentPage(pageable.getPageNumber())
//                .setTotalPage(modelPages.getTotalPages())
//                .setData(list);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CampaignDetailDto update(CampaignDetailDto dto) {
        CampaignDetail model = campaignDetailRepository.findById(dto.getId()).orElseThrow(
                () -> new OwnerException("修改的部门不存在！")
        );
        //model.setName(dto.getName()).setDescription(dto.getDescription()).setUpdateTime(new Date());
        modelMapper.map(dto, model);
        return modelMapper.map(campaignDetailRepository.save(model), CampaignDetailDto.class);
    }

    // 通过introId获取detail信息
    @Override
    public CampaignDetailDto getDetail(Long id) {
        CampaignIntroDto introDto = campaignIntroService.getByPrimaryKey(id);
        CampaignDetail campaignDetail = campaignDetailRepository.findById(introDto.getCampaignDetailId()).get();
        CampaignDetailDto campaignDetailDto = campaignDetailMapper.toCampaignDetailDto(campaignDetail)
                .setSupportCount(campaignDetail.getPayOrderList().size())
                .setCampaignIntro(introDto.setSupportCount(campaignDetail.getPayOrderList().size()))
                .setStory(modelMapper.map(campaignDetail.getJournalList().get(0),JournalDto.class));
        return  campaignDetailDto;
    }
}
