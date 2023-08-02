package com.carambola.service.Implementation;

import com.carambola.model.Category;
import com.carambola.repository.CategoryRepository;
import com.carambola.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> getCategoriesByCatalog(Long id) {
        return categoryRepository.findByCatalogId(id);
    }



}
