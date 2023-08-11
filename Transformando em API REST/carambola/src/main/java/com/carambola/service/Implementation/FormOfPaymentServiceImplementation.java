package com.carambola.service.Implementation;


import com.carambola.exception.ResponseModel;
import com.carambola.model.FormOfPayment;
import com.carambola.model.User;
import com.carambola.model.form.customer.FormOfPaymentForm;
import com.carambola.repository.FormOfPaymentRepository;
import com.carambola.repository.UserRepository;
import com.carambola.service.CustomerService;
import com.carambola.service.FormOfPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormOfPaymentServiceImplementation implements FormOfPaymentService {

    @Autowired
    private FormOfPaymentRepository formOfPaymentRepository;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public FormOfPayment insert(Long idUser, FormOfPaymentForm formOfPaymentForm) {
        FormOfPayment formOfPayment = new FormOfPayment();

        formOfPayment.setSecurityCode(formOfPaymentForm.getSecurityCode());
        formOfPayment.setCardHolderName(formOfPaymentForm.getCardHolderName());

        formOfPayment.setCardNumber(formOfPaymentForm.getCardNumber());
        if (formOfPayment.validateCard(formOfPayment.getCardNumber())){
            formOfPaymentRepository.save(formOfPayment);
            customerService.insertFormOfPayment(idUser, formOfPayment);
            return formOfPayment;
        } else {
            System.out.println("Forma de pagamento Inválida!");
            return null;
        }
    }

    @Override
    public ResponseEntity SearchById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()&&user.get().getFormOfPayment()!=null){
            Optional<FormOfPayment> formOfPayment = formOfPaymentRepository.findById(user.get().getFormOfPayment().getId());
            if(formOfPayment.isPresent()){
                return ResponseEntity.ok(formOfPayment);
            } else {
                ResponseModel notPresentFormOfPayment = new ResponseModel(404, "Não foi possível encontrar forma de pagamento cadastrada nesse usuário!");
                return new ResponseEntity(notPresentFormOfPayment, HttpStatus.NOT_FOUND);
            }
        } else {
            ResponseModel notPresentUser = new ResponseModel(404, "Não foi possível encontrar esse usuário, para localizar a forma de pagamento!");
            return new ResponseEntity(notPresentUser, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity delete(Long idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent()){
            Optional<FormOfPayment> formOfPayment = formOfPaymentRepository.findById(user.get().getFormOfPayment().getId());
            if(formOfPayment.isPresent()){
                user.get().setFormOfPayment(null);
                userRepository.save(user.get());
                formOfPaymentRepository.delete(formOfPayment.get());
                ResponseModel okDelete = new ResponseModel(200, "Forma de Pagamento (ID:" + formOfPayment.get().getId() + ", Númedo do Cartão:" + formOfPayment.get().getCardNumber() + ", Bandeira:" + formOfPayment.get().getCardFlag() + ", Nome do titular do cartão: " + formOfPayment.get().getCardHolderName() + ". Foi deletado com sucesso!");
                return new ResponseEntity(okDelete, HttpStatus.OK);
            } else {
                ResponseModel notPresentFormOfPayment = new ResponseModel(404, "Essa forma de pagamento não pode ser deletado, pois não foi encontrado!");
                return new ResponseEntity(notPresentFormOfPayment, HttpStatus.NOT_FOUND);
            }
        } else {
            ResponseModel notPresentUser = new ResponseModel(404, "Não foi possível encontrar esse usuário!");
            return new ResponseEntity(notPresentUser, HttpStatus.NOT_FOUND);
        }
    }
}
