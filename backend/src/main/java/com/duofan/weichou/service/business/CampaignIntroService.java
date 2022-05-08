package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.CampaignIntroCondition;
import com.duofan.weichou.dto.model.business.CampaignDetailDto;
import com.duofan.weichou.dto.model.business.CampaignIntroDto;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.model.business.CampaignIntro;

import java.util.List;
import java.util.Optional;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */

public interface CampaignIntroService {

    void removeByPrimaryKey(Long[] primaryKey);

    CampaignIntroDto save(CampaignIntroDto dto);

    CampaignIntroDto getByPrimaryKey(Long primaryKey);

    PageDto<CampaignIntroDto> findPageByCondition(CampaignIntroCondition condition);

    CampaignIntroDto update(CampaignIntroDto dto);

    List<CampaignIntroDto> getCurrentUserCampaignIntro();
}
