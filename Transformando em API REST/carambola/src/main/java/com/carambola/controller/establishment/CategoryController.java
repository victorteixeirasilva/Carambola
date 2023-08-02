package com.carambola.controller.establishment;

import com.carambola.model.Catalog;
import com.carambola.model.Category;
import com.carambola.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/establishment/catalog/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{idCatalog}")
    public Iterable<Category> getCategoriesByCatalog(@PathVariable Long idCatalog){
        return categoryService.getCategoriesByCatalog(idCatalog);
    }

    


}
