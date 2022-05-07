package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.AssetCondition;
import com.duofan.weichou.dto.model.business.AssetDto;
import com.duofan.weichou.dto.model.common.PageDto;

import java.util.List;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */

public interface AssetService {

    void removeByPrimaryKey(Long[] primaryKey);

    AssetDto save(AssetDto dto);

    void save(List<AssetDto> list, Long campaignDetailId);

    AssetDto getByPrimaryKey(Long primaryKey);

    PageDto<AssetDto> findPageByCondition(AssetCondition condition);

    AssetDto update(AssetDto dto);
}
