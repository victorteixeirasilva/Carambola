package com.carambola.controller.customer;

import com.carambola.model.FormOfPayment;
import com.carambola.model.form.customer.FormOfPaymentForm;
import com.carambola.service.FormOfPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/customer/payment")
public class FormOfPaymentController {

    @Autowired
    private FormOfPaymentService formOfPaymentService;

    @PostMapping("/{idUser}")
    public ResponseEntity<FormOfPayment> insert(@PathVariable Long idUser, @RequestBody FormOfPaymentForm formOfPaymentForm){
        FormOfPayment formOfPayment = formOfPaymentService.insert(idUser, formOfPaymentForm);
        return ResponseEntity.ok(formOfPayment);
    }




}
