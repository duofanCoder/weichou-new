package com.duofan.weichou.repository.business;


import com.duofan.weichou.model.business.Perk;
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
public interface PerkRepository extends PagingAndSortingRepository<Perk, Long>, CrudRepository<Perk, Long>, JpaSpecificationExecutor<Perk> {
    Page<Perk> findByTitleContaining(String title, Pageable pageable);
}
