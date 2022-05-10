package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.CampaignIntroCondition;
import com.duofan.weichou.dto.mapper.business.CampaignDetailMapper;
import com.duofan.weichou.dto.mapper.business.CampaignIntroMapper;
import com.duofan.weichou.dto.mapper.common.UserMapper;
import com.duofan.weichou.dto.model.business.CampaignIntroDto;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.dto.model.common.UserDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.business.CampaignIntro;
import com.duofan.weichou.model.business.Category;
import com.duofan.weichou.model.common.User;
import com.duofan.weichou.repository.business.CampaignDetailRepository;
import com.duofan.weichou.repository.business.CampaignIntroRepository;
import com.duofan.weichou.repository.common.UserRepository;
import com.duofan.weichou.service.common.UserService;
import io.swagger.models.Model;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
public class CampaignIntroServiceImpl implements CampaignIntroService {
    @Autowired
    private CampaignIntroRepository campaignIntroRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CampaignDetailRepository campaignDetailRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private CampaignIntroMapper campaignIntroMapper;

    @Autowired
    private CampaignDetailMapper campaignDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long[] primaryKey) {
        for (int i = 0; i < primaryKey.length; i++) {
            campaignIntroRepository.deleteById(primaryKey[i]);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 新增操作
    public CampaignIntroDto save(CampaignIntroDto dto) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto user = userService.findUserByUsername(email);
        CampaignIntro model = modelMapper.map(dto, CampaignIntro.class);
        model.setCreateTime(new Date()).setUpdateTime(new Date()).setEye(false).setOwner(new User().setId(user.getId())).setCurrentMoney(0d)
                .setCampaignDetail(new CampaignDetail().setCreateTime(new Date()).setUpdateTime(new Date()));
        CampaignIntro save = campaignIntroRepository.save(model);
        CampaignDetail campaignDetail =model.getCampaignDetail()
                .setCampaignIntro(new CampaignIntro().setId(save.getId()));
        campaignDetailRepository.save(campaignDetail);
        return modelMapper.map(save, CampaignIntroDto.class);
    }

    @Override
    public CampaignIntroDto getByPrimaryKey(Long primaryKey) {
        CampaignIntro intro = campaignIntroRepository.findById(primaryKey).get();
        CampaignIntroDto dto = modelMapper.map(intro, CampaignIntroDto.class);
        return dto.setOwner(dto.getOwner().setCampaignCount(intro.getOwner().getCampaignIntroList().size()));
    }

    @Override
    public PageDto<CampaignIntroDto> findPageByCondition(CampaignIntroCondition condition) {
        LinkedList<CampaignIntroDto> list = new LinkedList<>();
        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize());
        Specification<CampaignIntro> spec = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (!condition.getTitle().isEmpty()&&condition.getTitle().trim().length() != 0) {
                predicateList.add(criteriaBuilder.like(root.get("title").as(String.class), "%" + condition.getTitle() + "%"));
            }
            if (condition.getCategoryId()!=0) {
                predicateList.add(criteriaBuilder.equal(root.get("category").as(Category.class), new Category().setId(condition.getCategoryId())));
            }
            if (condition.getIsFinish()!=null&&condition.getIsFinish()) {
                predicateList.add(criteriaBuilder.lessThan(root.get("endTime").as(Date.class), new Date()));
            }else{
                predicateList.add(criteriaBuilder.greaterThan(root.get("endTime").as(Date.class), new Date()));
            }
            return criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()])).getRestriction();
        };
        Page<CampaignIntro> modelPages = campaignIntroRepository.findAll(spec, pageable);
        for (CampaignIntro campaign : modelPages.getContent()) {
            list.add(modelMapper.map(campaign, CampaignIntroDto.class));
        }
        return new PageDto<CampaignIntroDto>()
                .setCurrentPage(pageable.getPageNumber())
                .setTotalPage(modelPages.getTotalPages())
                .setData(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CampaignIntroDto update(CampaignIntroDto dto) {
        CampaignIntro model = campaignIntroRepository.findById(dto.getId()).orElseThrow(
                () -> new OwnerException("修改的部门不存在！")
        );
        //model.setName(dto.getName()).setDescription(dto.getDescription()).setUpdateTime(new Date());
//        modelMapper.map(dto, model);
//        model.setOwner(new User().setId(user.getId()));
        model.setTotalMoney(dto.getTotalMoney());
        return modelMapper.map(campaignIntroRepository.save(model), CampaignIntroDto.class);
    }

    @Override
    public List<CampaignIntroDto> getCurrentUserCampaignIntro() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto user = userService.findUserByUsername(email);
        List<CampaignIntro> list = campaignIntroRepository.findCampaignIntroByOwner(new User().setId(user.getId()));
        LinkedList dtoList = new LinkedList<CampaignIntroDto>();
        for (CampaignIntro campaignIntro : list) {
            dtoList.add(campaignIntroMapper.toCampaignIntroDto(campaignIntro));
        }
        return dtoList;
    }

}
