package com.carambola.service;

import com.carambola.model.Catalog;
import com.carambola.model.form.establishment.CatalogForm;
import org.springframework.stereotype.Component;

@Component
public interface CatalogService {

    public Catalog insert(Long id, CatalogForm catalogForm);
}
