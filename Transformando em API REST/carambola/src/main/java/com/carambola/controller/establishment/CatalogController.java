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

    @GetMapping("/{idEstablishment}")
    public Iterable<Catalog> getCatalogs(@PathVariable Long idEstablishment){
        return catalogService.getCatalogs(idEstablishment);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return catalogService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catalog> update(@PathVariable Long id, @RequestBody CatalogForm catalogForm){
        return ResponseEntity.ok(catalogService.update(id,catalogForm));
    }


}
