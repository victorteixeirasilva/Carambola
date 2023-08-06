package com.carambola.controller;

import com.carambola.controller.UserController;
import com.carambola.model.User;
import com.carambola.service.UserService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testFetchAllSuccess() {
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
        when(userService.fetchAll()).thenReturn(ResponseEntity.ok(userList));

        // Executar o método a ser testado
        ResponseEntity responseEntity = userController.fetchAll();

        // Verificar o resultado
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userList, responseEntity.getBody());
    }

    @Test
    public void testFetchAllFailure() {
        // Configurar comportamento simulado do serviço para lançar uma exceção
        when(userService.fetchAll()).thenThrow(new RuntimeException("Erro ao buscar usuários"));

        // Executar o método a ser testado
        ResponseEntity responseEntity = userController.fetchAll();

        // Verificar o resultado
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
