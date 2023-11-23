package com.zerobase.realestate.repository;

import com.zerobase.realestate.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Long> {

  boolean existsById(String id);

}