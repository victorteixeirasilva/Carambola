package com.carambola.controller.establishment;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Catalog;
import com.carambola.model.Category;
import com.carambola.model.form.establishment.CategoryForm;
import com.carambola.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/establishment/catalog/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{idCatalog}")
    public ResponseEntity getCategoriesByCatalog(@PathVariable Long idCatalog){
        try {
            return categoryService.getCategoriesByCatalog(idCatalog);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi possível mostrar as catégorias!");
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryForm categoryForm){
        return ResponseEntity.ok(categoryService.insert(categoryForm));
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<Category> update(@PathVariable Long idCategory, @RequestBody CategoryForm categoryForm){
        return ResponseEntity.ok(categoryService.update(idCategory, categoryForm));
    }

    @DeleteMapping("/{idCategory}")
    public String delete(@PathVariable Long idCategory){
        return categoryService.delete(idCategory);
    }




}
