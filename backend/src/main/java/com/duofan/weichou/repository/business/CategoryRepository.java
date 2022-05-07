package com.duofan.weichou.repository.business;


import com.duofan.weichou.model.business.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, CrudRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Page<Category> findByNameContaining(String title, Pageable pageable);
}
