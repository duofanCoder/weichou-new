package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.CampaignDetailCondition;
import com.duofan.weichou.dto.model.business.CampaignDetailDto;
import com.duofan.weichou.dto.model.common.PageDto;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */

public interface CampaignDetailService {

    void removeByPrimaryKey(Long[] primaryKey);

    CampaignDetailDto save(CampaignDetailDto dto);

    CampaignDetailDto getByPrimaryKey(Long primaryKey);

    PageDto<CampaignDetailDto> findPageByCondition(CampaignDetailCondition condition);

    CampaignDetailDto update(CampaignDetailDto dto);

    CampaignDetailDto getDetail(Long id);
}
