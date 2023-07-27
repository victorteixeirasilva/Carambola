package com.carambola.service.Implementation;

import com.carambola.model.Catalog;
import com.carambola.model.User;
import com.carambola.model.form.establishment.CatalogForm;
import com.carambola.repository.CatalogRepository;
import com.carambola.repository.UserRepository;
import com.carambola.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogServiceImplementation implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Catalog insert(Long id, CatalogForm catalogForm) {
        Catalog catalog = new Catalog();
        catalog.setName(catalogForm.getName());

        Optional<User> userBd = userRepository.findById(id);
        catalog.setUser(userBd.get());

        catalogRepository.save(catalog);
        return catalog;
    }

    @Override
    public Iterable<Catalog> getCatalogs(Long id) {
        return catalogRepository.findByUserId(id);
    }
}
