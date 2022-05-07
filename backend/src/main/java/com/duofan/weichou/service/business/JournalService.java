package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.JournalCondition;
import com.duofan.weichou.dto.model.business.JournalDto;
import com.duofan.weichou.dto.model.common.PageDto;

import java.util.List;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */

public interface JournalService {

    void removeByPrimaryKey(Long[] primaryKey);

    JournalDto save(JournalDto dto);

    JournalDto getByPrimaryKey(Long primaryKey);

    PageDto<JournalDto> findPageByCondition(JournalCondition condition);

    JournalDto update(JournalDto dto);

    void save(List<JournalDto> journalList, Long id);
}
