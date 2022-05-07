package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.AssetCondition;
import com.duofan.weichou.dto.mapper.business.AssetMapper;
import com.duofan.weichou.dto.model.business.AssetDto;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.business.Asset;
import com.duofan.weichou.repository.business.AssetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */
@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AssetMapper assetMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long[] primaryKey) {
        for (int i = 0; i < primaryKey.length; i++) {
            assetRepository.deleteById(primaryKey[i]);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 新增操作
    public AssetDto save(AssetDto dto) {
        Asset model = assetMapper.toAssetModel(dto);
        model.setCreateTime(new Date()).setUpdateTime(new Date()).setId(-1L);
        return modelMapper.map(assetRepository.save(model), AssetDto.class);
    }

    @Override
    public void save(List<AssetDto> list, Long campaignDetailId) {
        if (list == null) {
            return;
        }
        for (AssetDto d : list) {
            this.save(d.setCampaignDetailId(campaignDetailId));
        }
        return;
    }

    @Override
    public AssetDto getByPrimaryKey(Long primaryKey) {
        return modelMapper.map(assetRepository.findById(primaryKey), AssetDto.class);
    }

    @Override
    public PageDto<AssetDto> findPageByCondition(AssetCondition condition) {
//        LinkedList<AssetDto> list = new LinkedList<>();
//        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize());
//        Page<Asset> modelPages = assetRepository.findByTitleContaining(condition.getName(), pageable);
//        for (Asset campaign : modelPages.getContent()) {
//            list.add(modelMapper.map(campaign, AssetDto.class));
//        }
//        return new PageDto<AssetDto>()
//                .setCurrentPage(pageable.getPageNumber())
//                .setTotalPage(modelPages.getTotalPages())
//                .setData(list);

        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetDto update(AssetDto dto) {
        Asset model = assetRepository.findById(dto.getId()).orElseThrow(
                () -> new OwnerException("修改的部门不存在！")
        );
        //model.setName(dto.getName()).setDescription(dto.getDescription()).setUpdateTime(new Date());
        modelMapper.map(dto, model);
        return modelMapper.map(assetRepository.save(model), AssetDto.class);
    }
}
