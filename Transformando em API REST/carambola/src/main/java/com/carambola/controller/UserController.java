package com.carambola.controller;

import com.carambola.model.User;
import com.carambola.model.form.UserForm;
import com.carambola.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @description Controlador que exibe os end-points de contexto de "users" para ser consumido pelo
 * front-end.
 * Esse {@link org.springframework.web.bind.annotation.RestController} representa  nossa <b>Facate</b>, pois  abstrai toda
 * a complexidade de integração (Banco de Dados e API do ViaCEP) em uma interface simples e coesa (API REST).
 *
 * @author victorteixeirasilva 24/julho/2023
 *
 * @version 1.0
 *
 * */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<User> fetchAll(){
        return userService.fetchAll();
    }

    @PostMapping
    public ResponseEntity<UserForm> insert(@Valid @RequestBody UserForm userForm){
        userService.insert(userForm);
        return ResponseEntity.ok(userForm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> SearchById(@PathVariable Long id){
        return ResponseEntity.ok(userService.SearchById(id));
    }



}
