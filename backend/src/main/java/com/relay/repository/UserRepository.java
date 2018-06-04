package com.relay.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.relay.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Find user by id
     * 
     * @param id
     *            user id
     * @return user
     */
    User findById(@Param("id") Long id);

    /**
     * Delete user by id
     * 
     * @param id
     *            user id
     */
    void deleteById(@Param("id") Long id);
}
