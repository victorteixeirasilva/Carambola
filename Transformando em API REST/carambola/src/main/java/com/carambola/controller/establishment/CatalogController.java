package com.carambola.controller.establishment;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Catalog;
import com.carambola.model.form.establishment.CatalogForm;
import com.carambola.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/establishment/catalog")
public class CatalogController {
    @Autowired
    private CatalogService catalogService;


    @PostMapping("/{idEstablishment}")
    public ResponseEntity insert(@PathVariable Long idEstablishment, @RequestBody CatalogForm catalogForm){
        try {
            return catalogService.insert(idEstablishment, catalogForm);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(500, "Não foi possível inserir esse catalogo!");
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idEstablishment}")
    public ResponseEntity getCatalogs(@PathVariable Long idEstablishment){
        try {
            return catalogService.getCatalogs(idEstablishment);
        } catch (Exception ex){
            ResponseModel responseModel = new ResponseModel(500, "Não foi possível pegas os catalogos!");
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            return catalogService.delete(id);
        } catch (Exception ex){
            ResponseModel responseModel = new ResponseModel(500, "Não foi possível deletar o catalogo (id:" + id + ")");
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idCatalog}")
    public ResponseEntity update(@PathVariable Long idCatalog, @RequestBody CatalogForm catalogForm){
        try {
            return catalogService.update(idCatalog,catalogForm);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(500, "Não foi possível atualizar o catalogo!");
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
