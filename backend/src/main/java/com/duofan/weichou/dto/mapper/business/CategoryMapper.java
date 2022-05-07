package com.duofan.weichou.dto.mapper.business;

import cn.hutool.core.util.ObjectUtil;
import com.duofan.weichou.dto.model.business.CategoryDto;
import com.duofan.weichou.model.business.Category;
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
public class CategoryMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CategoryDto toCategoryDto(Category model) {
        if (ObjectUtil.isEmpty(model)) {
            return null;
        }
        return modelMapper.map(model,CategoryDto.class);
    }
}
