package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.PerkCondition;
import com.duofan.weichou.dto.model.business.PerkDto;
import com.duofan.weichou.dto.model.common.PageDto;

import java.util.List;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */

public interface PerkService {

    void removeByPrimaryKey(Long[] primaryKey);

    PerkDto save(PerkDto dto);

    PerkDto getByPrimaryKey(Long primaryKey);

    PageDto<PerkDto> findPageByCondition(PerkCondition condition);

    PerkDto update(PerkDto dto);

    void save(List<PerkDto> perkList, Long id);
}
