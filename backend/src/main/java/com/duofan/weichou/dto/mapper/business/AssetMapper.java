package com.duofan.weichou.dto.mapper.business;

import cn.hutool.core.util.ObjectUtil;
import com.duofan.weichou.dto.model.business.AssetDto;
import com.duofan.weichou.model.business.Asset;
import com.duofan.weichou.model.business.CampaignDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/4/4
 */
@Component
public class AssetMapper {
    @Autowired
    private ModelMapper modelMapper;

    public AssetDto toAssetDto(Asset model) {

        if (ObjectUtil.isEmpty(model)) {
            return null;
        }
        return modelMapper.map(model, AssetDto.class);
    }

    public Asset toAssetModel(AssetDto model) {
        if (ObjectUtil.isEmpty(model)) {
            return null;
        }
        Asset map = modelMapper.map(model, Asset.class);
        map.setCampaignDetail(new CampaignDetail().setId(model.getCampaignDetailId()));
        return map;
    }

    public List<AssetDto> toAssetDtoList(List<Asset> assetList) {
        if (ObjectUtil.isEmpty(assetList)) {
            return null;
        }
        LinkedList<AssetDto> list = new LinkedList<>();
        for (Asset asset : assetList) {
            list.add(toAssetDto(asset));
        }
        return list;
    }

    public List<Asset> toModelList(List<AssetDto> assetList, Long id) {
        if (ObjectUtil.isEmpty(assetList)) {
            return null;
        }
        LinkedList<Asset> list = new LinkedList<>();
        for (AssetDto asset : assetList) {
            list.add(toAssetModel(asset.setCampaignDetailId(id)));
        }
        return list;
    }
}
