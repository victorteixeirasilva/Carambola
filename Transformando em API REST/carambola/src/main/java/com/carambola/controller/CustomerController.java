package com.carambola.controller;

import com.carambola.model.User;
import com.carambola.model.form.customer.CustomerUpdateForm;
import com.carambola.service.CustomerService;
import com.carambola.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.carambola.model.form.customer.CustomerForm;

@RestController
@RequestMapping("user/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerForm> insert(@Valid @RequestBody CustomerForm customerForm){
        customerService.insert(customerForm);
        return ResponseEntity.ok(customerForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateForm customerUpdateForm){
        return ResponseEntity.ok(customerService.update(id, customerUpdateForm));
    }



}
