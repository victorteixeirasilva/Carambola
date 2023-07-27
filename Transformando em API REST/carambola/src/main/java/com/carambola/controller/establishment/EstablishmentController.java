package com.carambola.controller.establishment;

import com.carambola.model.User;
import com.carambola.model.form.establishment.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<EstablishmentForm> insert(@Valid @RequestBody EstablishmentForm establishmentForm){
        establishmentService.insert(establishmentForm);
        return ResponseEntity.ok(establishmentForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody EstablishmentUpdateForm establishmentUpdateForm){
        return ResponseEntity.ok(establishmentService.update(id, establishmentUpdateForm));
    }


}
