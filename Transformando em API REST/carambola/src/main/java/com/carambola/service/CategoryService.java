package com.carambola.service;

import com.carambola.model.Category;
import com.carambola.model.form.establishment.CategoryForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CategoryService {

    public ResponseEntity delete(Long idCategory);

    public ResponseEntity update(Long idCategory, CategoryForm categoryForm);

    public ResponseEntity insert(CategoryForm categoryForm);

    public ResponseEntity getCategoriesByCatalog(Long id);

}
