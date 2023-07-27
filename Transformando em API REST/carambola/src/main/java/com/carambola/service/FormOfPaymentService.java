package com.carambola.service;

import com.carambola.model.FormOfPayment;
import com.carambola.model.form.customer.FormOfPaymentForm;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface FormOfPaymentService {

    public FormOfPayment insert(Long idUser, FormOfPaymentForm formOfPaymentForm);

    public Optional<FormOfPayment> SearchById(Long id);

    public String delete(Long idUser);

}
