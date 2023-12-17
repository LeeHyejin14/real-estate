package com.zerobase.realestate.repository;

import com.zerobase.realestate.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsById(String id);

  Optional<User> findById(String id);

}