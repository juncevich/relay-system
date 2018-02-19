package com.relay.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.relay.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

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
     * @return deleted user
     */
    User deleteById(@Param("id") Long id);
}
