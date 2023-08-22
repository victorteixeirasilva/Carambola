package com.carambola.service;

import com.carambola.model.Catalog;
import com.carambola.model.form.establishment.CatalogForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CatalogService {

    public ResponseEntity insert(Long id, CatalogForm catalogForm);

    public ResponseEntity getCatalogs(Long id);

    public ResponseEntity delete(Long id);

    public ResponseEntity update(Long id, CatalogForm catalogForm);
}
