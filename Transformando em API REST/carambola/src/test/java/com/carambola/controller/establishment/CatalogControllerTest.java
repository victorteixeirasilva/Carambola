package com.carambola.controller.establishment;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Address;
import com.carambola.model.Catalog;
import com.carambola.model.User;
import com.carambola.model.form.establishment.CatalogForm;
import com.carambola.service.CatalogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CatalogControllerTest {

    @Mock
    private CatalogService catalogService;

    @InjectMocks
    private CatalogController catalogController;

    private CatalogForm mockCatalogForm(){
        CatalogForm catalogForm = new CatalogForm();
        catalogForm.setName("Catalogo01");

        return catalogForm;
    }

    private User mockEstablishment() {
        User userEstablishment = new User();
        userEstablishment.setId(1L);
        userEstablishment.setName("MC");
        userEstablishment.setCnpj("42591651000143");
        userEstablishment.setEmail("contato@mc.com");
        userEstablishment.setPassword("vi230803");
        userEstablishment.setTelephone(1158147251);
        Address address = new Address("04917-140","Rua João Chammas","Casa2","Jardim Souza","São Paulo","SP","3550308","1004","11","7107");
        userEstablishment.setAddress(address);
        userEstablishment.setHouseNumber("402");

        return userEstablishment;
    }

    private Catalog mockCatalog(){
        User user = mockEstablishment();
        CatalogForm catalogForm = mockCatalogForm();
        Catalog catalog = new Catalog();
        catalog.setId(1);
        catalog.setName(catalogForm.getName());
        catalog.setUser(user);
        catalog.setActive(true);

        return catalog;
    }

    private ResponseEntity mockListCatalogsByEstablishmente(){
        List<Catalog> catalogs = new ArrayList<>();
        catalogs.add(mockCatalog());

        return ResponseEntity.ok(catalogs);
    }

    @Test
    public void testUpdate(){
        Catalog catalog = mockCatalog();
        catalog.setName("Nome01");

        when(catalogService.update(1L, mockCatalogForm())).thenReturn(ResponseEntity.ok(mockCatalog()));

        ResponseEntity expectedResponse = ResponseEntity.ok(mockCatalog());
        ResponseEntity actualResponse = catalogService.update(1L, mockCatalogForm());

        verify(catalogService, times(1)).update(1L, mockCatalogForm());

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testDelete(){
        Catalog catalog = mockCatalog();
        catalog.setActive(false);
        when(catalogService.delete(1L)).thenReturn(ResponseEntity.ok(catalog));

        ResponseEntity expectedResponse = ResponseEntity.ok(catalog);
        ResponseEntity actualResponse = catalogService.delete(1L);

        verify(catalogService, times(1)).delete(1L);

        Assertions.assertEquals(expectedResponse, actualResponse);

    }


    @Test
    public void testGetCatalogs_Ok(){
        when(catalogService.getCatalogs(1L)).thenReturn(mockListCatalogsByEstablishmente());

        ResponseEntity expectedResponse = mockListCatalogsByEstablishmente();
        ResponseEntity actualResponse = catalogService.getCatalogs(1L);

        verify(catalogService, times(1)).getCatalogs(1L);

        Assertions.assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void testGetCatalogs_Error(){
        when(catalogService.getCatalogs(2L)).thenReturn(new ResponseEntity(new ResponseModel(500, "Não foi possível pegas os catalogos!"), HttpStatus.INTERNAL_SERVER_ERROR));

        ResponseEntity expectedResponse = new ResponseEntity(new ResponseModel(500, "Não foi possível pegas os catalogos!"), HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity actualResponse = catalogService.getCatalogs(2L);

        verify(catalogService, times(1)).getCatalogs(2L);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testInsert_Ok(){
        User user = mockEstablishment();
        CatalogForm catalogForm = mockCatalogForm();
        Catalog catalog = new Catalog();
        catalog.setId(1);
        catalog.setUser(user);
        catalog.setName(catalogForm.getName());
        catalog.setActive(true);

        when(catalogService.insert(user.getId(),catalogForm)).thenReturn(ResponseEntity.ok(catalog));

        ResponseEntity actualResponse = catalogService.insert(user.getId(), catalogForm);
        ResponseEntity expectedResponse = ResponseEntity.ok(catalog);

        verify(catalogService, times(1)).insert(user.getId(), catalogForm);

        Assertions.assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void testInsert_Error(){
        User user = mockEstablishment();
        CatalogForm catalogForm = mockCatalogForm();
        Catalog catalog = new Catalog();
        catalog.setId(1);
        catalog.setUser(user);
        catalog.setName(catalogForm.getName());
        catalog.setActive(true);


        when(catalogService.insert(2L, catalogForm)).thenReturn(new ResponseEntity(new ResponseModel(500, "Não foi possível inserir esse catalogo!"), HttpStatus.INTERNAL_SERVER_ERROR));

        ResponseEntity actualResponse = catalogService.insert(2L, catalogForm);
        ResponseEntity expectedResponse = new ResponseEntity(new ResponseModel(500, "Não foi possível inserir esse catalogo!"), HttpStatus.INTERNAL_SERVER_ERROR);

        verify(catalogService, times(1)).insert(2L, catalogForm);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }





}
