package com.carambola.service;

import com.carambola.model.Address;
import com.carambola.model.Role;
import com.carambola.model.User;
import com.carambola.service.Implementation.UserServiceImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
public class UserServiceTest {

    @Test
    public void testSearchById(){
        UserServiceImplementation userServiceImplementation = new UserServiceImplementation();

        ResponseEntity responseEntity = userServiceImplementation.SearchById(2L);
        User user = new User();
        user.setId(2l);
        user.setTelephone(1158147251);
        user.setEmail("contato@mc.com");
        user.setPassword("vi230803");
        user.setName("MC");
        user.setHouseNumber("374");

        Address address = new Address();
        address.setCep("04917-150");
        address.setLogradouro("Rua Hadim");
        address.setComplemento("");
        address.setBairro("Jardim Souza");
        address.setLocalidade("São Paulo");
        address.setUf("SP");
        address.setIbge("3550308");
        address.setGia("1004");
        address.setDdd("11");
        address.setSiafi("11");

        user.setAddress(address);
        user.setCnpj("42591651000143");

        Role role = new Role();
        role.setId(1);
        role.setRole("ROLE_ESTABLISHMENT");

        user.setRole(role);

        user.setActive(true);

        ResponseEntity responseEntity1 = ResponseEntity.ok(user);

        Assertions.assertEquals(responseEntity,responseEntity1);
    }
}
