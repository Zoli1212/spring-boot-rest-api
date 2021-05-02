package com.sha.springbootcrudrestfulwebservices.repository;

import com.sha.springbootcrudrestfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
