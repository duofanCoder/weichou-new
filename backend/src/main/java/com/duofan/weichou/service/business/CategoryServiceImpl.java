package com.duofan.weichou.service.business;


import com.duofan.weichou.controller.v1.condition.business.CategoryCondition;
import com.duofan.weichou.dto.model.business.CategoryDto;
import com.duofan.weichou.dto.model.common.PageDto;
import com.duofan.weichou.exception.type.OwnerException;
import com.duofan.weichou.model.business.Category;
import com.duofan.weichou.repository.business.CategoryRepository;
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
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long[] primaryKey) {
        for (int i = 0; i < primaryKey.length; i++) {
            categoryRepository.deleteById(primaryKey[i]);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    // 新增操作
    public CategoryDto save(CategoryDto dto) {
        Category model = modelMapper.map(dto, Category.class);
        model.setCreateTime(new Date()).setUpdateTime(new Date());
        return modelMapper.map(categoryRepository.save(model), CategoryDto.class);
    }

    @Override
    public CategoryDto getByPrimaryKey(Long primaryKey) {
        return modelMapper.map(categoryRepository.findById(primaryKey), CategoryDto.class);
    }

    @Override
    public PageDto<CategoryDto> findPageByCondition(CategoryCondition condition) {
        LinkedList<CategoryDto> list = new LinkedList<>();
        PageRequest pageable = PageRequest.of(condition.getPageNum(), condition.getPageSize());
        Page<Category> modelPages = categoryRepository.findByNameContaining(condition.getName(), pageable);
        for (Category campaign : modelPages.getContent()) {
            list.add(modelMapper.map(campaign, CategoryDto.class));
        }
        return new PageDto<CategoryDto>()
                .setCurrentPage(pageable.getPageNumber())
                .setTotalPage(modelPages.getTotalPages())
                .setData(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CategoryDto update(CategoryDto dto) {
        Category model = categoryRepository.findById(dto.getId()).orElseThrow(
                () -> new OwnerException("修改的部门不存在！")
        );
        //model.setName(dto.getName()).setDescription(dto.getDescription()).setUpdateTime(new Date());
        modelMapper.map(dto,model);
        return modelMapper.map(categoryRepository.save(model), CategoryDto.class);
    }

}
