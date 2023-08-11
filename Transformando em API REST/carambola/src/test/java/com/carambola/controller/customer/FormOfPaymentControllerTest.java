package com.carambola.controller.customer;

import com.carambola.model.FormOfPayment;
import com.carambola.model.form.customer.FormOfPaymentForm;
import com.carambola.service.FormOfPaymentService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.carambola.exception.ResponseModel;
import com.carambola.model.User;
import com.carambola.model.form.customer.CustomerForm;
import com.carambola.model.form.customer.CustomerUpdateForm;
import com.carambola.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class FormOfPaymentControllerTest {

    @InjectMocks
    private FormOfPaymentController formOfPaymentController;

    @Mock
    private FormOfPaymentService formOfPaymentService;

    @Test
    public void testInsertSuccess() {
        // Criar objeto simulado para entrada
        Long userId = 1L;
        FormOfPaymentForm formOfPaymentForm = new FormOfPaymentForm();
        formOfPaymentForm.setCardHolderName("Victor Teixeira Silva");
        formOfPaymentForm.setCardNumber("5502097455733541");
        formOfPaymentForm.setSecurityCode(329);

        // Configurar comportamento simulado do serviço
        FormOfPayment expectedFormOfPayment = new FormOfPayment();
        expectedFormOfPayment.setCardNumber(formOfPaymentForm.getCardNumber());
        expectedFormOfPayment.setCardHolderName(formOfPaymentForm.getCardHolderName());
        expectedFormOfPayment.setSecurityCode(formOfPaymentForm.getSecurityCode());

        when(formOfPaymentService.insert(userId, formOfPaymentForm)).thenReturn(expectedFormOfPayment);

        // Executar o método a ser testado
        ResponseEntity<FormOfPayment> responseEntity = formOfPaymentController.insert(userId, formOfPaymentForm);

        // Verificar se o serviço foi chamado
        verify(formOfPaymentService, times(1)).insert(userId, formOfPaymentForm);

        // Verificar se a resposta esperada foi retornada
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedFormOfPayment, responseEntity.getBody());
    }

    @Test
    public void testDeleteSuccess() {
        // Criar um ID de usuário simulado
        Long userId = 1L;

        // Configurar comportamento simulado do serviço para uma resposta de sucesso
        ResponseEntity<String> successResponse = ResponseEntity.ok("Forma de pagamento excluída com sucesso");
        when(formOfPaymentService.delete(userId)).thenReturn(successResponse);

        // Executar o método a ser testado
        ResponseEntity<String> responseEntity = formOfPaymentController.delete(userId);

        // Verificar se o serviço foi chamado
        verify(formOfPaymentService, times(1)).delete(userId);

        // Verificar se a resposta esperada foi retornada
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Forma de pagamento excluída com sucesso", responseEntity.getBody());
    }

    @Test
    public void testDeleteException() {
        // Criar um ID de usuário simulado
        Long userId = 1L;

        // Configurar comportamento simulado do serviço para lançar uma exceção
        ResponseEntity expectedResponse = new ResponseEntity(
                new ResponseModel(500, "Erro ao deletar a forma de pagamento!"),
                HttpStatus.INTERNAL_SERVER_ERROR);
        when(formOfPaymentService.delete(userId)).thenThrow(new RuntimeException("Erro ao deletar"));

        // Executar o método a ser testado
        ResponseEntity<String> responseEntity = formOfPaymentController.delete(userId);

        // Verificar se o serviço foi chamado
        verify(formOfPaymentService, times(1)).delete(userId);

        // Verificar se a resposta de erro esperada foi retornada
        assertEquals(expectedResponse, responseEntity);
    }

    @Test
    public void testSearchByIdSuccess() {
        // Criar um ID de usuário simulado
        Long userId = 1L;

        // Configurar comportamento simulado do serviço para uma resposta de sucesso
        FormOfPayment formOfPayment = new FormOfPayment();
        formOfPayment.setCardHolderName("Victor Teixeira Silva");
        formOfPayment.setCardNumber("5502097455733541");
        formOfPayment.setSecurityCode(329);
        when(formOfPaymentService.SearchById(userId)).thenReturn(ResponseEntity.ok(formOfPayment));

        // Executar o método a ser testado
        ResponseEntity responseEntity = formOfPaymentController.searchById(userId);

        // Verificar se o serviço foi chamado
        verify(formOfPaymentService, times(1)).SearchById(userId);

        // Verificar se a resposta esperada foi retornada
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(formOfPayment, responseEntity.getBody());
        assertEquals(responseEntity,ResponseEntity.ok(formOfPayment));
    }

    @Test
    public void testSearchByIdException() {
        // Criar um ID de usuário simulado
        Long userId = 1L;

        // Configurar comportamento simulado do serviço para lançar uma exceção
        ResponseEntity expectedEntity = new ResponseEntity<>(new ResponseModel(500,"Erro ao buscar a forma de pagamento!"), HttpStatus.INTERNAL_SERVER_ERROR);
        when(formOfPaymentService.SearchById(userId)).thenThrow(new RuntimeException("Erro ao buscar"));

        // Executar o método a ser testado
        ResponseEntity responseEntity = formOfPaymentController.searchById(userId);

        // Verificar se o serviço foi chamado
        verify(formOfPaymentService, times(1)).SearchById(userId);

        // Verificar se a resposta de erro esperada foi retornada
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(expectedEntity, responseEntity);
    }

}
