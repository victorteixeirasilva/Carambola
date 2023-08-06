package com.carambola.controller;

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
import static org.mockito.Mockito.*;

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

    @Test
    public void testSearchByIdSuccess() {
        // Configurar comportamento simulado do serviço
        Long userId = 1L;
        User userExpected = new User();
        userExpected.setId(1L);
        userExpected.setName("Victor");
        userExpected.setActive(true);
        ResponseEntity expectedResponse = ResponseEntity.ok(userExpected);
        when(userService.SearchById(userId)).thenReturn(expectedResponse);

        // Executar o método a ser testado
        ResponseEntity responseEntity = userController.SearchById(userId);

        // Verificar se o serviço foi chamado com o ID correto
        verify(userService, times(1)).SearchById(userId);

        // Verificar se a resposta esperada foi retornada
        assertEquals(expectedResponse, responseEntity);
    }

    @Test
    public void testSearchByIdException() {
        // Configurar comportamento simulado do serviço para lançar uma exceção
        Long userId = 1L;
        when(userService.SearchById(userId)).thenThrow(new RuntimeException("Erro simulado"));

        // Executar o método a ser testado
        ResponseEntity responseEntity = userController.SearchById(userId);

        // Verificar se a resposta de erro esperada foi retornada
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteSuccess() {
        // Configurar comportamento simulado do serviço
        Long userId = 1L;
        ResponseEntity expectedResponse = ResponseEntity.ok("Usuário deletado com sucesso");
        when(userService.delete(userId)).thenReturn(expectedResponse);

        // Executar o método a ser testado
        ResponseEntity responseEntity = userController.delete(userId);

        // Verificar se o serviço foi chamado com o ID correto
        verify(userService, times(1)).delete(userId);

        // Verificar se a resposta esperada foi retornada
        assertEquals(expectedResponse, responseEntity);
    }

    @Test
    public void testDeleteError() {
        // Configurar comportamento simulado do serviço para lançar uma exceção
        Long userId = 1L;
        when(userService.delete(userId)).thenThrow(new RuntimeException("Erro ao deletar usuário"));

        // Executar o método a ser testado
        ResponseEntity responseEntity = userController.delete(userId);

        // Verificar se o serviço foi chamado com o ID correto
        verify(userService, times(1)).delete(userId);

        // Verificar se a resposta de erro esperada foi retornada
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

}
