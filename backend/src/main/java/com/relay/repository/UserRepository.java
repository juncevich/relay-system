package com.relay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.relay.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(@Param("id") Long id);

    User deleteById(@Param("id") Long id);
}
