package com.carambola.service.Implementation;


import com.carambola.model.FormOfPayment;
import com.carambola.model.form.FormOfPaymentForm;
import com.carambola.repository.FormOfPaymentRepository;
import com.carambola.service.CustomerService;
import com.carambola.service.FormOfPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormOfPaymentServiceImplementation implements FormOfPaymentService {

    @Autowired
    private FormOfPaymentRepository formOfPaymentRepository;
    @Autowired
    private CustomerService customerService;


    @Override
    public FormOfPayment insert(Long idUser, FormOfPaymentForm formOfPaymentForm) {
        FormOfPayment formOfPayment = new FormOfPayment();

        formOfPayment.setSecurityCode(formOfPaymentForm.getSecurityCode());
        formOfPayment.setCardHolderName(formOfPaymentForm.getCardHolderName());

        formOfPayment.setCardNumber(formOfPaymentForm.getCardNumber());
        formOfPayment.validateCard(formOfPayment.getCardNumber());

        formOfPaymentRepository.save(formOfPayment);
        customerService.insertFormOfPayment(idUser, formOfPayment);


        return formOfPayment;
    }

    @Override
    public Optional<FormOfPayment> SearchById(Long id) {
        return Optional.empty();
    }

    @Override
    public String delete(Long id) {
        return null;
    }
}
