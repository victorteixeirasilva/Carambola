package com.carambola.controller.customer;

import com.carambola.exception.ResponseModel;
import com.carambola.model.FormOfPayment;
import com.carambola.model.form.customer.FormOfPaymentForm;
import com.carambola.service.FormOfPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @DeleteMapping("/{idUser}")
    public ResponseEntity delete(@PathVariable Long idUser){
        try {
            return formOfPaymentService.delete(idUser);
        } catch (Exception exception){
            ResponseModel responseModel = new ResponseModel(500,"Erro ao deletar a forma de pagamento!");
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idUser}")
    public ResponseEntity searchById(@PathVariable Long idUser){
        try {
            return formOfPaymentService.SearchById(idUser);
        } catch (Exception exception){
            ResponseModel responseModel = new ResponseModel(500,"Erro ao buscar a forma de pagamento!");
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
