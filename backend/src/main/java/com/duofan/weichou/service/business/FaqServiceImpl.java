package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.FaqCondition;
import com.duofan.weichou.dto.mapper.business.FaqMapper;
import com.duofan.weichou.dto.model.business.FaqDto;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.business.Faq;
import com.duofan.weichou.repository.business.FaqRepository;
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
public class FaqServiceImpl implements FaqService {
    @Autowired
    private FaqRepository faqRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FaqMapper faqMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long[] primaryKey) {
        for (int i = 0; i < primaryKey.length; i++) {
            faqRepository.deleteById(primaryKey[i]);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 新增操作
    public FaqDto save(FaqDto dto) {
        Faq model = faqMapper.toFaqModel(dto);
        model.setCreateTime(new Date()).setUpdateTime(new Date());
        return faqMapper.toFaqDto(faqRepository.save(model));
    }

    @Override
    public void save(List<FaqDto> list, Long campaignDetailId) {
        if (list == null) {
            return;
        }
        for (FaqDto faqDto : list) {
            this.save(faqDto.setCampaignDetailId(campaignDetailId));
        }
    }

    @Override
    public FaqDto getByPrimaryKey(Long primaryKey) {
        return modelMapper.map(faqRepository.findById(primaryKey), FaqDto.class);
    }

    @Override
    public PageDto<FaqDto> findPageByCondition(FaqCondition condition) {
//        LinkedList<FaqDto> list = new LinkedList<>();
//        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize());
//        Page<Faq> modelPages = faqRepository.findByNameContaining(condition.getName(), pageable);
//        for (Faq campaign : modelPages.getContent()) {
//            list.add(modelMapper.map(campaign, FaqDto.class));
//        }
//        return new PageDto<FaqDto>()
//                .setCurrentPage(pageable.getPageNumber())
//                .setTotalPage(modelPages.getTotalPages())
//                .setData(list);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FaqDto update(FaqDto dto) {
        Faq model = faqRepository.findById(dto.getId()).orElseThrow(
                () -> new OwnerException("修改的部门不存在！")
        );
        //model.setName(dto.getName()).setDescription(dto.getDescription()).setUpdateTime(new Date());
        modelMapper.map(dto, model);
        return modelMapper.map(faqRepository.save(model), FaqDto.class);
    }

}
