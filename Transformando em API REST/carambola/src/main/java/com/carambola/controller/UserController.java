package com.carambola.controller;

import com.carambola.model.User;
import com.carambola.model.form.UserForm;
import com.carambola.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @description Controlador que exibe os end points de contexto de "users" para ser consumido pelo
 * front-end.
 *
 * @author victorteixeirasilva 24/julho/2023
 *
 * @version 1.0
 *
 *
 * */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<User> allUsers(){
        return userService.allUsers();
    }

    @PostMapping
    public ResponseEntity<UserForm> insert(@Valid @RequestBody UserForm userForm){
        userService.insert(userForm);
        return ResponseEntity.ok(userForm);
    }



}
