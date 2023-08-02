package com.carambola.service.Implementation;

import com.carambola.model.Category;
import com.carambola.model.form.establishment.CategoryForm;
import com.carambola.repository.CategoryRepository;
import com.carambola.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category insert(CategoryForm categoryForm) {
        Category category = new Category();
        category.setName(categoryForm.getName());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Iterable<Category> getCategoriesByCatalog(Long id) {
        return categoryRepository.findByCatalogId(id);
    }



}
