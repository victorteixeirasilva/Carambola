package com.carambola.service;

import com.carambola.model.Category;
import com.carambola.model.form.establishment.CategoryForm;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CategoryService {

    public Category update(Long idCategory, CategoryForm categoryForm);

    public Category insert(CategoryForm categoryForm);

    public Iterable<Category> getCategoriesByCatalog(Long id);

}
