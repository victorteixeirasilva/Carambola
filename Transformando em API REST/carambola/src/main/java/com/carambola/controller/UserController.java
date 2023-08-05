package com.carambola.controller;

import com.carambola.model.User;
import com.carambola.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @description Controlador que exibe os end-points de contexto de qualquer "users" para ser consumido pelo
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

    @GetMapping("/{id}")
    public ResponseEntity SearchById(@PathVariable Long id){
        try {
            return userService.SearchById(id);
        } catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            return userService.delete(id);
        } catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
