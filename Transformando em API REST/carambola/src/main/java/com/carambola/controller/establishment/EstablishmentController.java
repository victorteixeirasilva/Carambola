package com.carambola.controller.establishment;

import com.carambola.exception.ResponseModel;
import com.carambola.model.User;
import com.carambola.model.form.establishment.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.carambola.service.EstablishmentService;


/**
 * @description Controlador que exibe os end points de contexto de "Establishment" para ser consumido pelo
 * front-end.
 *
 * @author victorteixeirasilva 24/julho/2023
 *
 * @version 1.0
 *
 *
 * */

@RestController
@RequestMapping("user/establishment")
public class EstablishmentController {
    @Autowired
    private EstablishmentService establishmentService;

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody EstablishmentForm establishmentForm){
        try{
            return ResponseEntity.ok(establishmentService.insert(establishmentForm));
        } catch (Exception ex){
            ResponseModel responseModel = new ResponseModel(500,"Não foi possível inserir o estabelecimento!");
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody EstablishmentUpdateForm establishmentUpdateForm){
       try {
           return ResponseEntity.ok(establishmentService.update(id, establishmentUpdateForm));
       } catch (Exception exception) {
           ResponseModel responseModel = new ResponseModel(500,"Não foi possível atualizar o estabelecimento!");
           return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }


}
