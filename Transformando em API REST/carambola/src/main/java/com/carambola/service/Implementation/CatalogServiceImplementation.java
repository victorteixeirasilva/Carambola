package com.carambola.service.Implementation;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Catalog;
import com.carambola.model.Category;
import com.carambola.model.User;
import com.carambola.model.form.establishment.CatalogForm;
import com.carambola.model.form.establishment.CategoryForm;
import com.carambola.repository.CatalogRepository;
import com.carambola.repository.CategoryRepository;
import com.carambola.repository.UserRepository;
import com.carambola.service.CatalogService;
import com.carambola.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogServiceImplementation implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;
    @Override
    public ResponseEntity insert(Long id, CatalogForm catalogForm) {
        Optional<User> userBd = userRepository.findById(id);
        if (userBd.isPresent()){
            Catalog catalog = new Catalog();
            catalog.setName(catalogForm.getName());
            catalog.setUser(userBd.get());

            catalogRepository.save(catalog);

            CategoryForm categoryForm = new CategoryForm();
            categoryForm.setName("Todos os Produtos");
            categoryForm.setIdCatalog(catalog.getId());
            categoryForm.setIdParentCategory(0L);
            categoryService.insert(categoryForm);

            return ResponseEntity.ok(catalog);
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi possível criar o catalogo, pois não encontramos o usuário (id:" + id + ")!"
            );
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity getCatalogs(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            List<Catalog> catalogs = (List<Catalog>) catalogRepository.findByUserIdAndActiveTrue(id);
            if (!catalogs.isEmpty()){
                return ResponseEntity.ok(catalogs);
            } else {
                ResponseModel notFoundCatalog = new ResponseModel(404, "Não foi possível localizar catalogos nesse estabelecimento!");
                return new ResponseEntity(notFoundCatalog, HttpStatus.NOT_FOUND);
            }
        } else {
            ResponseModel notFoundEstablishment = new ResponseModel(404, "Não foi possível encontrar o estabelecimento informado!");
            return new ResponseEntity(notFoundEstablishment, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Catalog> delete(Long id) {
        Optional<Catalog> catalog = catalogRepository.findById(id);
        if(catalog.isPresent()){
            List<Category> category = (List<Category>) categoryRepository.findByCatalogId(id);
            if(catalogRepository.hasCategories(id)){
                var isPresentCategory = new ResponseModel(500, "Não é possível deletar pois existem categorias vinculadas!");
                return new ResponseEntity(isPresentCategory, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                catalog.get().setActive(false);
                catalogRepository.save(catalog.get());
                return  ResponseEntity.ok(catalog.get());
            }
        } else {
            ResponseModel notFound = new ResponseModel(404, "Não foi possível encontrar um catalogo com esse id!");
            return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity update(Long id, CatalogForm catalogForm) {
        Optional<Catalog> catalogBd = catalogRepository.findById(id);
        Catalog catalog = new Catalog();
        if(catalogBd.isPresent()){
            if(catalogBd.get().getName()==catalogForm.getName()||catalogForm.getName()==""){
                return ResponseEntity.ok(catalogBd.get());
            } else {
                catalog.setName(catalogForm.getName());
                catalog.setId(catalogBd.get().getId());
                catalog.setUser(catalogBd.get().getUser());
                catalogRepository.save(catalog);
                return ResponseEntity.ok(catalog);
            }
        } else {
            ResponseModel responseModel = new ResponseModel(404, "Não foi possível editar esse catalogo, pois o mesmo não foi encontrado!");
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }
}
