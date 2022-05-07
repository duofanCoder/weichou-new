package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.FaqCondition;
import com.duofan.weichou.dto.model.business.FaqDto;
import com.duofan.weichou.dto.model.common.PageDto;

import java.util.List;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */

public interface FaqService {

    void removeByPrimaryKey(Long[] primaryKey);

    FaqDto save(FaqDto dto);

    void save(List<FaqDto> list ,Long campaignDetailId);

    FaqDto getByPrimaryKey(Long primaryKey);

    PageDto<FaqDto> findPageByCondition(FaqCondition condition);

    FaqDto update(FaqDto dto);
}
