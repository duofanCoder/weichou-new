package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.PayOrderCondition;
import com.duofan.weichou.dto.model.business.PayOrderDto;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.business.PayOrder;
import com.duofan.weichou.repository.business.PayOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {
    @Autowired
    private PayOrderRepository payOrderRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long[] primaryKey) {
        for (int i = 0; i < primaryKey.length; i++) {
            payOrderRepository.deleteById(primaryKey[i]);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 新增操作
    public PayOrderDto save(PayOrderDto dto) {
        PayOrder model = modelMapper.map(dto, PayOrder.class);
        model.setCreateTime(new Date()).setUpdateTime(new Date());
        return modelMapper.map(payOrderRepository.save(model), PayOrderDto.class);
    }

    @Override
    public PayOrderDto getByPrimaryKey(Long primaryKey) {
        return modelMapper.map(payOrderRepository.findById(primaryKey), PayOrderDto.class);
    }

    @Override
    public PageDto<PayOrderDto> findPageByCondition(PayOrderCondition condition) {
//        LinkedList<PayOrderDto> list = new LinkedList<>();
//        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize());
//        Page<PayOrder> modelPages = payOrderRepository.findByNameContaining(condition.getName(), pageable);
//        for (PayOrder campaign : modelPages.getContent()) {
//            list.add(modelMapper.map(campaign, PayOrderDto.class));
//        }
//        return new PageDto<PayOrderDto>()
//                .setCurrentPage(pageable.getPageNumber())
//                .setTotalPage(modelPages.getTotalPages())
//                .setData(list);
        return  null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayOrderDto update(PayOrderDto dto) {
        PayOrder model = payOrderRepository.findById(dto.getId()).orElseThrow(
                () -> new OwnerException("修改的部门不存在！")
        );
        //model.setName(dto.getName()).setDescription(dto.getDescription()).setUpdateTime(new Date());
        modelMapper.map(dto,model);
        return modelMapper.map(payOrderRepository.save(model), PayOrderDto.class);
    }

}
