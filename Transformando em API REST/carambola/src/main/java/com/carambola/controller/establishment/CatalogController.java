package com.carambola.controller.establishment;

import com.carambola.model.Catalog;
import com.carambola.model.form.establishment.CatalogForm;
import com.carambola.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
