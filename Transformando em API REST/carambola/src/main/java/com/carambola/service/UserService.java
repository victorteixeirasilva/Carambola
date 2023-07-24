package com.carambola.service;

import com.carambola.model.User;
import org.springframework.stereotype.Component;


/**
 * @description Interface que define o padrão <b>Strategy</b> no domínio do User. Com
 * isso, se necessário, podemos ter multíplas implementações dessa mesma
 * interface.
 *
 * @author victorteixeirasilva 24/julho/2023
 *
 * @version 1.0
 *
 *
 * */

@Component
public interface UserService {

    public Iterable<User> allUsers();

    public void insert(User user);

    public void update(Long id, User user);

    public void delete(Long id);



}
