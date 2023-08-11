package com.carambola.service.Implementation;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Catalog;
import com.carambola.model.User;
import com.carambola.repository.UserRepository;
import com.carambola.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public ResponseEntity SearchById(Long id) {
        Optional<User> user = userRepository.findByIdAndActiveTrue(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else {
            ResponseModel responseModel = new ResponseModel(404, "Não é possível encontrar esse usuário!");
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity fetchAll() {
        List<User> allUsers = userRepository.findAllAndActiveTrue();
        if (!allUsers.isEmpty()){
            return ResponseEntity.ok(allUsers);
        } else {
            ResponseModel responseModel = new ResponseModel(404, "Não foi possível encontrar nenhum usuário!");
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            if(userRepository.hasCatalog(id)){
                ResponseModel isPresentCatalog = new ResponseModel(500, "Não é possível deletar esse usuário pois existem catalogos vinculados!");
                return new ResponseEntity(isPresentCatalog, HttpStatus.INTERNAL_SERVER_ERROR);
            } else if(userRepository.hasFormOfPayment(id)){
                ResponseModel isPresentFormOfPayment = new ResponseModel(500, "Não é possível deletar esse usuário pois existem formas de pagamento vinculadas!");
                return new ResponseEntity(isPresentFormOfPayment, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                user.get().setActive(false);
                userRepository.save(user.get());
                return ResponseEntity.ok(user.get());
            }
        } else {
            ResponseModel notFound = new ResponseModel(404, "Esse usuário não pode ser deletado, pois não existe!");
            return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
        }
    }


}
