package com.cwm.studentmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.cwm.studentmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String u, String p);
}