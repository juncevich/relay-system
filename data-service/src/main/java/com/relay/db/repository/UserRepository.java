package com.relay.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.relay.web.model.User;
import org.springframework.stereotype.Repository;

@Repository
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
