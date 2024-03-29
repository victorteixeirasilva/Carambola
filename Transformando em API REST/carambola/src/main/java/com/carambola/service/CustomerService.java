package com.carambola.service;

import com.carambola.model.FormOfPayment;
import com.carambola.model.User;
import com.carambola.model.form.customer.CustomerForm;
import com.carambola.model.form.customer.CustomerUpdateForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CustomerService {

    public ResponseEntity showEstablishments();

    public ResponseEntity update(Long id, CustomerUpdateForm customerUpdateForm);

    public User insertFormOfPayment(Long id, FormOfPayment formOfPayment);

    public void insert(CustomerForm userForm);
}
