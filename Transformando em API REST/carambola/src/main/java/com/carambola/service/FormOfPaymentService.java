package com.carambola.service;

import com.carambola.model.FormOfPayment;
import com.carambola.model.form.customer.FormOfPaymentForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface FormOfPaymentService {

    public FormOfPayment insert(Long idUser, FormOfPaymentForm formOfPaymentForm);

    public ResponseEntity SearchById(Long id);

    public ResponseEntity delete(Long idUser);

}
