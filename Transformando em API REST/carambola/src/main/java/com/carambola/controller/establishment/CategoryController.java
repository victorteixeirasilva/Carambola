package com.carambola.controller.establishment;

import com.carambola.model.Catalog;
import com.carambola.model.Category;
import com.carambola.model.form.establishment.CategoryForm;
import com.carambola.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/establishment/catalog/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{idCatalog}")
    public Iterable<Category> getCategoriesByCatalog(@PathVariable Long idCatalog){
        return categoryService.getCategoriesByCatalog(idCatalog);
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryForm categoryForm){
        return ResponseEntity.ok(categoryService.insert(categoryForm));
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category){
        return null;
    }




}
