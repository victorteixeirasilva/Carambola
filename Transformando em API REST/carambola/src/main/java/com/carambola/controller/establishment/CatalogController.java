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
    public ResponseEntity<Catalog> insert(@PathVariable Long idEstablishment, @RequestBody CatalogForm catalogForm){
        Catalog catalog = catalogService.insert(idEstablishment, catalogForm);
        return ResponseEntity.ok(catalog);
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
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catalog> update(@PathVariable Long id, @RequestBody CatalogForm catalogForm){
        return ResponseEntity.ok(catalogService.update(id,catalogForm));
    }



}
