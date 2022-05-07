package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.CategoryCondition;
import com.duofan.weichou.dto.model.business.CategoryDto;
import com.duofan.weichou.dto.model.common.PageDto;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */

public interface CategoryService {

    void removeByPrimaryKey(Long[] primaryKey);

    CategoryDto save(CategoryDto dto);

    CategoryDto getByPrimaryKey(Long primaryKey);

    PageDto<CategoryDto> findPageByCondition(CategoryCondition condition);

    CategoryDto update(CategoryDto dto);
}
