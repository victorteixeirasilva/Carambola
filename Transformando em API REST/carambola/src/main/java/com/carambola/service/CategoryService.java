package com.carambola.service;

import com.carambola.model.Category;
import org.springframework.stereotype.Component;

@Component
public interface CategoryService {

    public Iterable<Category> getCategoriesByCatalog(Long id);

}
