package com.duofan.weichou.dto.mapper.business;

import cn.hutool.core.util.ObjectUtil;
import com.duofan.weichou.dto.model.business.PayOrderDto;
import com.duofan.weichou.model.business.PayOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/4/4
 */
@Component
public class PayOrderMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PayOrderDto toPayOrderDto(PayOrder model) {
        if (ObjectUtil.isEmpty(model)) {
            return null;
        }
        return modelMapper.map(model,PayOrderDto.class);
    }
}
