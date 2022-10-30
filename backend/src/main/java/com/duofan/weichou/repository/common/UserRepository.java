package com.duofan.weichou.repository.common;

import com.duofan.weichou.model.business.CampaignDetail;
import com.duofan.weichou.model.common.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Arpit Khandelwal.
 */
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);
}
