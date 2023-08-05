package com.carambola.controller.customer;

import com.carambola.exception.ResponseModel;
import com.carambola.model.User;
import com.carambola.model.form.customer.CustomerUpdateForm;
import com.carambola.service.CustomerService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.carambola.model.form.customer.CustomerForm;

@RestController
@RequestMapping("user/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("establishment")
    public ResponseEntity  showEstablishments(){
        try {
            return customerService.showEstablishments();
        } catch (Exception ex){
            ResponseModel responseModel = new ResponseModel(404,"Não existem estabelicimentos cadastrados!");
            return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<CustomerForm> insert(@Valid @RequestBody CustomerForm customerForm){
        customerService.insert(customerForm);
        return ResponseEntity.ok(customerForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateForm customerUpdateForm){
        try {
            return customerService.update(id, customerUpdateForm);
        } catch (Exception ex){
            ResponseModel responseModel = new ResponseModel(404,"Não existem estabelicimentos cadastrados!");
            return new ResponseEntity(responseModel,HttpStatus.NOT_FOUND);
        }
    }



}
