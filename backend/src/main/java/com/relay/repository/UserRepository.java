package com.relay.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.relay.model.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

    /**
     * Find user by id
     * 
     * @param id
     *            user id
     * @return user
     */
    Mono<User> findById(@Param("id") Long id);

    /**
     * Delete user by id
     * 
     * @param id
     *            user id
     */
    void deleteById(@Param("id") Long id);
}
