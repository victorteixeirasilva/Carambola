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
    public ResponseEntity insert(@RequestBody CategoryForm categoryForm){
        try {
            return categoryService.insert(categoryForm);
        } catch (Exception exception) {
            ResponseModel responseModel = new ResponseModel(
                    500,
                    "Não foi possível inserir a categoria!");
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity update(@PathVariable Long idCategory, @RequestBody CategoryForm categoryForm){
        try {
            return categoryService.update(idCategory, categoryForm);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(
                    500,
                    "Não foi possível atualizar a catégoria!");
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity delete(@PathVariable Long idCategory){
        try {
            return categoryService.delete(idCategory);
        } catch (Exception e){
            ResponseModel responseModel = new ResponseModel(
                    500,
                    "Não foi possivel deletar a categoria.");
            return new ResponseEntity(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
