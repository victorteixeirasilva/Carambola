package com.carambola.controller.establishment;

import com.carambola.model.Address;
import com.carambola.model.Catalog;
import com.carambola.model.User;
import com.carambola.model.form.establishment.CatalogForm;
import com.carambola.service.CatalogService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CatalogControllerTest {

    @Mock
    private CatalogService catalogService;

    @InjectMocks
    private CatalogController catalogController;

    private CatalogForm mockCatalog(){
        CatalogForm catalogForm = new CatalogForm();
        catalogForm.setName("Catalogo01");

        return catalogForm;
    }

    private User mockEstablishment() {
        User userEstablishment = new User();
        userEstablishment.setName("MC");
        userEstablishment.setCnpj("42591651000143");
        userEstablishment.setCnpj("contato@mc.com");
        userEstablishment.setPassword("vi230803");
        userEstablishment.setTelephone(1158147251);
        Address address = new Address("04917-140","Rua João Chammas","Casa2","Jardim Souza","São Paulo","SP","3550308","1004","11","7107");
        userEstablishment.setAddress(address);
        userEstablishment.setHouseNumber("402");

        return userEstablishment;
    }

}
