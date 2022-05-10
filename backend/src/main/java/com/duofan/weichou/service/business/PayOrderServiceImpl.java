package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.PayOrderCondition;
import com.duofan.weichou.dto.mapper.business.CampaignIntroMapper;
import com.duofan.weichou.dto.mapper.business.PayOrderMapper;
import com.duofan.weichou.dto.model.business.CampaignDetailDto;
import com.duofan.weichou.dto.model.business.CampaignIntroDto;
import com.duofan.weichou.dto.model.business.PayOrderDto;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.dto.model.common.UserDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.business.CampaignIntro;
import com.duofan.weichou.model.business.PayOrder;
import com.duofan.weichou.model.business.Perk;
import com.duofan.weichou.model.common.User;
import com.duofan.weichou.repository.business.CampaignIntroRepository;
import com.duofan.weichou.repository.business.PayOrderRepository;
import com.duofan.weichou.repository.business.PerkRepository;
import com.duofan.weichou.service.common.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
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
public class PayOrderServiceImpl implements PayOrderService {
    @Autowired
    private PayOrderRepository payOrderRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private CampaignIntroMapper campaignIntroMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CampaignIntroService campaignIntroService;

    @Autowired
    private CampaignDetailService campaignDetailService;


    @Autowired
    private PerkRepository perkRepository;

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
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto user = userService.findUserByUsername(email);
        PayOrder model = payOrderMapper.toModel(dto);
        model.setCreateTime(new Date()).setUpdateTime(new Date())
                .setOwner(new User().setId(user.getId()));
        model =payOrderRepository.save(model);
        PayOrderDto map = modelMapper.map(model, PayOrderDto.class);
//        可调整获取方式提高数据库查询性能
        CampaignDetailDto detail=campaignDetailService.getByPrimaryKey(model.getCampaignDetail().getId());
        CampaignIntroDto intro = campaignIntroService.getByPrimaryKey(detail.getCampaignIntroId());
        Perk perk = perkRepository.findById(dto.getPerkId()).get();
        campaignIntroService.update(intro.setCurrentMoney(intro.getCurrentMoney()+perk.getPrice()));
        return map;
    }

    @Override
    public PayOrderDto getByPrimaryKey(Long primaryKey) {
        return modelMapper.map(payOrderRepository.findById(primaryKey), PayOrderDto.class);
    }

    @Override
    public PageDto<PayOrderDto> findPageByCondition(PayOrderCondition condition) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto user = userService.findUserByUsername(email);
        LinkedList<PayOrderDto> list = new LinkedList<>();
        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize());
        List<PayOrder> modelPages = payOrderRepository.findPayOrderByOwner(new User().setId(user.getId()));
        for (PayOrder campaign : modelPages) {
            list.add(modelMapper.map(campaign, PayOrderDto.class)
                    .setCampaignIntro(campaignIntroMapper.toCampaignIntroDto(campaign.getCampaignDetail().getCampaignIntro()))
            );
        }
        return new PageDto<PayOrderDto>()
                .setCurrentPage(pageable.getPageNumber())
                .setTotalPage(modelPages.size())
                .setData(list);
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
