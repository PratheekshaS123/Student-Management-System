package com.cwm.studentmanagement.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cwm.studentmanagement.model.User;
import com.cwm.studentmanagement.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User login(String u, String p) {
        return repo.findByUsernameAndPassword(u, p);
    }
}