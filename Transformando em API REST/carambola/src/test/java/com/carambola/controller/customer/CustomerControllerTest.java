package com.carambola.controller.customer;
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
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    public void testShowEstablishmentsSuccess() {
        // Mock de dados
        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setId(1L);
        user1.setName("Victor");
        user1.setActive(true);

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Arthur");
        user2.setActive(true);

        userList.add(user1);
        userList.add(user2);

        // Configurar comportamento simulado do serviço
        ResponseEntity expectedResponse = ResponseEntity.ok(userList);
        when(customerService.showEstablishments()).thenReturn(expectedResponse);

        // Executar o método a ser testado
        ResponseEntity responseEntity = customerController.showEstablishments();

        // Verificar se o serviço foi chamado
        verify(customerService, times(1)).showEstablishments();

        // Verificar se a resposta esperada foi retornada
        assertEquals(expectedResponse, responseEntity);
    }

    @Test
    public void testShowEstablishmentsError() {
        // Configurar comportamento simulado do serviço para lançar uma exceção
        when(customerService.showEstablishments()).thenThrow(new RuntimeException("Erro ao buscar estabelecimentos"));

        // Executar o método a ser testado
        ResponseEntity responseEntity = customerController.showEstablishments();

        // Verificar se o serviço foi chamado
        verify(customerService, times(1)).showEstablishments();

        // Verificar se a resposta de erro esperada foi retornada
        ResponseModel expectedResponse = new ResponseModel(404, "Não existem estabelicimentos cadastrados!");
        assertEquals(new ResponseEntity<>(expectedResponse, HttpStatus.NOT_FOUND), responseEntity);
    }

    @Test
    public void testInsert() {
        // Criar objeto simulado para entrada
        CustomerForm customerForm = new CustomerForm();
        customerForm.setName("John");

        // Executar o método a ser testado
        ResponseEntity<CustomerForm> responseEntity = customerController.insert(customerForm);

        // Verificar se o serviço foi chamado
        verify(customerService, times(1)).insert(customerForm);

        // Verificar se a resposta esperada foi retornada
        assertEquals(ResponseEntity.ok(customerForm), responseEntity);
    }

    @Test
    public void testUpdateSuccess() {
        // Criar objeto simulado para entrada
        Long customerId = 1L;
        CustomerUpdateForm customerUpdateForm = new CustomerUpdateForm();
        customerUpdateForm.setName("Updated John");

        User user = new User();
        user.setId(customerId);
        user.setName(customerUpdateForm.getName());

        // Configurar comportamento simulado do serviço
        ResponseEntity expectedResponse = ResponseEntity.ok(user);
        when(customerService.update(customerId, customerUpdateForm)).thenReturn(expectedResponse);

        // Executar o método a ser testado
        ResponseEntity responseEntity = customerController.update(customerId, customerUpdateForm);

        // Verificar se o serviço foi chamado
        verify(customerService, times(1)).update(customerId, customerUpdateForm);

        // Verificar se a resposta esperada foi retornada
        assertEquals(expectedResponse, responseEntity);
    }

    @Test
    public void testUpdateError() {
        // Criar objeto simulado para entrada
        Long customerId = 1L;
        CustomerUpdateForm customerUpdateForm = new CustomerUpdateForm();
        customerUpdateForm.setName("Updated John");

        // Configurar comportamento simulado do serviço para lançar uma exceção
        when(customerService.update(customerId, customerUpdateForm)).thenThrow(new RuntimeException("Erro ao atualizar usuário"));

        // Executar o método a ser testado
        ResponseEntity responseEntity = customerController.update(customerId, customerUpdateForm);

        // Verificar se o serviço foi chamado
        verify(customerService, times(1)).update(customerId, customerUpdateForm);

        // Verificar se a resposta de erro esperada foi retornada
        ResponseModel expectedResponse = new ResponseModel(404, "Erro ao atualizar esse usuário!");
        assertEquals(new ResponseEntity<>(expectedResponse, HttpStatus.NOT_FOUND), responseEntity);
    }
}
