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
    public String delete(Long idCategory) {
        Optional<Category> category = categoryRepository.findById(idCategory);
        if(category.isPresent()){
            categoryRepository.delete(category.get());
            return "Categoria: (ID: " + category.get().getId() + ", Nome: " + category.get().getName() + "). Foi deletada com sucesso!";
        } else {
            return "Não é possível deletar essa categoria não existe!";
        }
    }

    @Override
    public Category update(Long idCategory, CategoryForm categoryForm) {
        Category category = new Category();
        Optional<Category> categoryBd = categoryRepository.findById(idCategory);

        //Name
        if(categoryBd.get().getName()!=categoryForm.getName()&&categoryForm.getName()!=""){
            category.setName(categoryForm.getName());
        } else {
            category.setName(categoryBd.get().getName());
        }

        //Parent Category
        if(categoryBd.get().getParentCategory().getId()!=categoryForm.getIdParentCategory()&&categoryForm.getIdParentCategory()!=0){
            Optional<Category> parentCategory = categoryRepository.findById(categoryForm.getIdParentCategory());
            category.setParentCategory(parentCategory.get());
        } else {
            category.setParentCategory(categoryBd.get().getParentCategory());
        }

        //Catalog
        category.setCatalog(categoryBd.get().getCatalog());

        //Id
        category.setId(categoryBd.get().getId());

        categoryRepository.save(category);

        return category;
    }

    @Override
    public Category insert(CategoryForm categoryForm) {
        Category category = new Category();
        category.setName(categoryForm.getName());

        Optional<Catalog> catalogBd = catalogRepository.findById(categoryForm.getIdCatalog());
        if(catalogBd.isPresent()){
            Catalog catalog = catalogBd.get();
            category.setCatalog(catalog);
        }

        if(categoryForm.getIdParentCategory()==0){
            Category parentCategory = categoryRepository.findCategoriesByCatalogIdAndName(category.getCatalog().getId());
            category.setParentCategory(parentCategory);
        } else {
            Optional<Category> parentCategoryBd = categoryRepository.findById(categoryForm.getIdParentCategory());
            if(parentCategoryBd.isPresent()){
                Category parentCategory = parentCategoryBd.get();
                category.setParentCategory(parentCategory);
            }
        }

        categoryRepository.save(category);
        return category;
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
