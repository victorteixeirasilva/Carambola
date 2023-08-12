package com.carambola.service.Implementation;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Catalog;
import com.carambola.model.Category;
import com.carambola.model.form.establishment.CategoryForm;
import com.carambola.repository.CatalogRepository;
import com.carambola.repository.CategoryRepository;
import com.carambola.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public ResponseEntity delete(Long idCategory) {
        Optional<Category> category = categoryRepository.findByIdAndActiveTrue(idCategory);
        if(category.isPresent()){
            category.get().setActive(false);
            categoryRepository.save(category.get());

            ResponseModel responseModel = new ResponseModel(
                    200,
                    "Catégoria:(Name: " + category.get().getName() +
                            " id: " + idCategory +
                            "), Deletada corretamente!"
            );
            return ResponseEntity.ok(responseModel);
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi possível deletar a categoria pois a mesma não foi encontrada");
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity update(Long idCategory, CategoryForm categoryForm) {
        Category category = new Category();
        Optional<Category> categoryBd = categoryRepository.findById(idCategory);
        if(categoryBd.isPresent()){
            //Name
            if(categoryBd.get().getName()!=categoryForm.getName()&&categoryForm.getName()!=""){
                category.setName(categoryForm.getName());
            } else {
                category.setName(categoryBd.get().getName());
            }

            //Parent Category
            if(categoryBd.get().getParentCategory().getId()!=categoryForm.getIdParentCategory()&&categoryForm.getIdParentCategory()!=0){
                Optional<Category> parentCategory = categoryRepository.findByIdAndActiveTrue(categoryForm.getIdParentCategory());
                if(parentCategory.isPresent()){
                    category.setParentCategory(parentCategory.get());
                } else {
                    ResponseModel responseModel = new ResponseModel(
                            404,
                            "Categoria pai informada (id:" + categoryForm.getIdParentCategory() + "),  não foi encontrada!");
                    return new ResponseEntity(responseModel,HttpStatus.NOT_FOUND);
                }
            } else {
                category.setParentCategory(categoryBd.get().getParentCategory());
            }

            //Catalog
            if (categoryForm.getIdCatalog()!=categoryBd.get().getCatalog().getId()&&categoryForm.getIdCatalog()!=0){
                Optional<Catalog> catalog = catalogRepository.findByIdAndActiveTrue(categoryForm.getIdCatalog());
                if (catalog.isPresent()){
                    category.setCatalog(catalog.get());
                } else {
                    ResponseModel responseModel = new ResponseModel(
                            404,
                            "Catalogo (id:" + catalog.get().getId() + "), não foi encontrado");
                    return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
                }
            } else {
                category.setCatalog(categoryBd.get().getCatalog());
            }

            //Id
            category.setId(categoryBd.get().getId());

            categoryRepository.save(category);

            return ResponseEntity.ok(category);
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi possível encontrar a catégoria informada, id:" + idCategory);
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity insert(CategoryForm categoryForm) {
        Category category = new Category();
        category.setName(categoryForm.getName());

        Optional<Catalog> catalogBd = catalogRepository.findById(categoryForm.getIdCatalog());
        if (catalogBd.isPresent()){
            Catalog catalog = catalogBd.get();
            category.setCatalog(catalog);

            if(categoryForm.getIdParentCategory()==0){
                Category parentCategory = categoryRepository.findCategoryAllProduct(category.getCatalog().getId());
                if (parentCategory != null){
                    category.setParentCategory(parentCategory);
                } else {
                    ResponseModel responseModel = new ResponseModel(
                            404,
                            "Não existe nenhuma categoria ativa nesse catalogo para ser a categoria pai!");
                    return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
                }
            } else {
                Optional<Category> parentCategoryBd = categoryRepository.findById(categoryForm.getIdParentCategory());
                if(parentCategoryBd.isPresent()){
                    Category parentCategory = parentCategoryBd.get();
                    category.setParentCategory(parentCategory);
                } else {
                    ResponseModel responseModel = new ResponseModel(
                            404,
                            "Categoria pai informada (id:" + categoryForm.getIdParentCategory() + "), não foi encontrada!");
                    return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
                }
            }

            categoryRepository.save(category);
            return ResponseEntity.ok(category);
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi possível encontrar o catalogo de id:" + categoryForm.getIdCatalog());
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity getCategoriesByCatalog(Long id) {
        Optional<Catalog> byId = catalogRepository.findById(id);
        if (byId.isPresent()){
            List<Category> byCatalogId = categoryRepository.findCategoriesByCatalogId(id);
            if(!byCatalogId.isEmpty()){
                return ResponseEntity.ok(byCatalogId);
            } else {
                ResponseModel responseModel = new ResponseModel(
                        404,
                        "Não existe nenhuma categoria nesse catalogo, que não seja a categoria base (Todos os produtos)!");
                return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
            }
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi possível encontrar o catalogo de id:" + id);
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }



}
