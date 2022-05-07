package com.duofan.weichou.repository.common;

import com.duofan.weichou.model.common.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Arpit Khandelwal.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
