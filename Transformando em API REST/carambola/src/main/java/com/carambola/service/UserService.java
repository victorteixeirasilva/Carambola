package com.carambola.service;

import com.carambola.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String allUsers(){
        return userRepository.findAll().toString();
    }
}
