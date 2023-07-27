package com.carambola.service.Implementation;

import com.carambola.model.User;
import com.carambola.repository.UserRepository;
import com.carambola.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Description Implementação da <b>Strategy</b> {@link  UserService}, a qual pode ser
 * injetada pelo Spring (via {@link org.springframework.beans.factory.annotation.Autowired}).
 * Com isso, como essa classe é um {@link org.springframework.stereotype.Service}, ela será
 * tratada como um <b>Singleton</b>.
 *  Essa classe do tipo service, serve para conter a regra de negócio.
 *
 * @author victorteixeirasilva 24/julho/2023
 *
 * @version 1.0
 * */

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> SearchById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user;
        }
        System.out.println("------------ ERRO: Usuário não existe! -------------");
        return user;
    }

    @Override
    public Iterable<User> fetchAll() {
        return userRepository.findAll();
    }


    @Override
    public String delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return "Usuário (ID:" + user.get().getId() + ", NAME:" + user.get().getName()
                    + ", EMAIL:" + user.get().getEmail() + ". Foi deletado com sucesso!";
        }
        return "Esse usuário não pode ser deletado, pois não existe!";
    }


}