package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.PayOrderCondition;
import com.duofan.weichou.dto.model.business.PayOrderDto;
import com.duofan.weichou.dto.model.common.PageDto;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */

public interface PayOrderService {

    void removeByPrimaryKey(Long[] primaryKey);

    PayOrderDto save(PayOrderDto dto);

    PayOrderDto getByPrimaryKey(Long primaryKey);

    PageDto<PayOrderDto> findPageByCondition(PayOrderCondition condition);

    PayOrderDto update(PayOrderDto dto);
}
