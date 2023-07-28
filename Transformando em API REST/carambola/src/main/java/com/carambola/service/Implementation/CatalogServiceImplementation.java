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

    @Override
    public String delete(Long id) {
        Optional<Catalog> catalog = catalogRepository.findById(id);
        if(catalog.isPresent()){
            catalogRepository.delete(catalog.get());
            return "Catalogo (ID:" + catalog.get().getId() + ", Nome:" + catalog.get().getName() + "). Foi deletado com sucesso!";
        } else {
            return "Não é possível deletar esse catalogo pois ele não existe!";
        }
    }

    @Override
    public Catalog update(Long id, CatalogForm catalogForm) {
        Optional<Catalog> catalogBd = catalogRepository.findById(id);
        Catalog catalog = new Catalog();
        if(catalogBd.isPresent()){
            if(catalogBd.get().getName()==catalogForm.getName()||catalogForm.getName()==""){
                return catalogBd.get();
            } else {
                catalog.setName(catalogForm.getName());
                catalog.setId(catalogBd.get().getId());
                catalog.setUser(catalogBd.get().getUser());
                catalogRepository.save(catalog);
                return catalog;
            }
        } else {
            return null;
        }
    }
}
