package com.relay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.relay.model.Relay;

@Repository
public interface RelayRepository extends JpaRepository<Relay, Long> {

    List<Relay> findByText(@Param("text") String text);
}
