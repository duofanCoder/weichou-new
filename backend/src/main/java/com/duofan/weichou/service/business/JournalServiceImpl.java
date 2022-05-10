package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.JournalCondition;
import com.duofan.weichou.dto.mapper.business.JournalMapper;
import com.duofan.weichou.dto.mapper.common.UserMapper;
import com.duofan.weichou.dto.model.business.JournalDto;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.dto.model.common.UserDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.business.Journal;
import com.duofan.weichou.repository.business.JournalRepository;
import org.hibernate.engine.query.spi.ParamLocationRecognizer;
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
public class JournalServiceImpl implements JournalService {
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JournalMapper journalMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long[] primaryKey) {
        for (int i = 0; i < primaryKey.length; i++) {
            journalRepository.deleteById(primaryKey[i]);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 新增操作
    public JournalDto save(JournalDto dto) {
        Journal model = journalMapper.toJournalModel(dto);
        model.setCreateTime(new Date()).setUpdateTime(new Date());
        return modelMapper.map(journalRepository.save(model), JournalDto.class);
    }

    @Override
    public JournalDto getByPrimaryKey(Long primaryKey) {
        Journal journal = journalRepository.findById(primaryKey).get();
        return modelMapper.map(journal, JournalDto.class).setOwner(userMapper.toDto(journal.getCampaignDetail().getCampaignIntro().getOwner()));
    }

    @Override
    public PageDto<JournalDto> findPageByCondition(JournalCondition condition) {
        LinkedList<JournalDto> list = new LinkedList<>();
        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize());
        Page<Journal> modelPages = journalRepository.findByTitleContaining(condition.getName(), pageable);
        for (Journal campaign : modelPages.getContent()) {
            list.add(modelMapper.map(campaign, JournalDto.class));
        }
        return new PageDto<JournalDto>()
                .setCurrentPage(pageable.getPageNumber())
                .setTotalPage(modelPages.getTotalPages())
                .setData(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JournalDto update(JournalDto dto) {
        Journal model = journalRepository.findById(dto.getId()).orElseThrow(
                () -> new OwnerException("修改的部门不存在！")
        );
        //model.setName(dto.getName()).setDescription(dto.getDescription()).setUpdateTime(new Date());
        modelMapper.map(dto, model);
        return modelMapper.map(journalRepository.save(model), JournalDto.class);
    }

    @Override
    public void save(List<JournalDto> journalList, Long id) {
        if (journalList == null) {
            return;
        }
        for (JournalDto journalDto : journalList) {
            this.save(journalDto.setCampaignDetailId(id));
        }
    }

}
