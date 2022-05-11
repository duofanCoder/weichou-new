package com.duofan.weichou.repository.business;


import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.business.Journal;
import com.duofan.weichou.model.enums.JournalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */
public interface JournalRepository extends PagingAndSortingRepository<Journal, Long>, CrudRepository<Journal, Long>, JpaSpecificationExecutor<Journal> {
    Page<Journal> findByTitleContaining(String title, Pageable pageable);
    List<Journal> findByCampaignDetailAndJournalType(CampaignDetail campaignDetail, JournalType journalType);
}
