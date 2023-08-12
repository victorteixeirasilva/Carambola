package com.carambola.service.Implementation;

import com.carambola.exception.ResponseModel;
import com.carambola.model.Category;
import com.carambola.model.Product;
import com.carambola.model.form.establishment.ProductForm;
import com.carambola.model.form.establishment.ProductUpdateForm;
import com.carambola.repository.CategoryRepository;
import com.carambola.repository.ProductRepository;
import com.carambola.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseEntity insert(ProductForm productForm) {
        Product product = new Product(productForm);

        Optional<Category> category = categoryRepository.findByIdAndActiveTrue(product.getCategory().getId());
        if (productForm.getIdCategory()!=0){
            if(category.isPresent()){
                product.setCategory(category.get());
                productRepository.save(product);
                return ResponseEntity.ok(product);
            } else {
                ResponseModel responseModel = new ResponseModel(
                        404,
                        "Não foi possível cadastrar o produto, pois a categoria informada não existe."
                );
                return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
            }
        } else {
            product.setCategory(categoryRepository.findCategoryAllProduct(category.get().getCatalog().getId()));
            productRepository.save(product);
            return ResponseEntity.ok(product);
        }
    }

    @Override
    public ResponseEntity getProduct(Long idProduct) {
        Optional<Product> product = productRepository.findByIdAndActiveTrue(idProduct);
        if(product.isPresent()){
            return ResponseEntity.ok(product);
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Produto (id:" + idProduct + "), não foi encontrado."
                    );
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity getProductsByCategory(Long idCategory) {
        Optional<Category> category = categoryRepository.findByIdAndActiveTrue(idCategory);
        if (category.isPresent()){
            return ResponseEntity.ok(productRepository.findByCategory(idCategory));
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi possível mostrar os produtos da categoria (id:" + idCategory + "), pois a mesma não foi encontrada"
            );
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity getProductsByCatalog(Long idCatalog) {
        Category categoryAllProduct = categoryRepository.findCategoryAllProduct(idCatalog);
        List<Product> allProducts = productRepository.findByCatalogAndAllProduct(idCatalog, categoryAllProduct.getId());
        if(!allProducts.isEmpty()){
            return ResponseEntity.ok(allProducts);
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi possível encontrar nenhum produto nesse catalogo!"
            );
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity getProductsNotStock(Long idCatalog) {
        Category categoryAllProduct = categoryRepository.findCategoryAllProduct(idCatalog);
        List<Product> allProductsNotStock = productRepository.findByAllProductHaveStockFalse(idCatalog, categoryAllProduct.getId());
        if(!allProductsNotStock.isEmpty()){
            return ResponseEntity.ok(allProductsNotStock);
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi possível encontrar nenhum produto sem estoque!"
            );
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity update(Long idProduct, ProductUpdateForm productUpdateForm) {
        Optional<Product> productBd = productRepository.findByIdAndActiveTrue(idProduct);
        if(productBd.isPresent()){
            Product product = new Product(productUpdateForm);
            product.setId(productBd.get().getId());

            if(Objects.equals(product.getName(), productBd.get().getName()) || Objects.equals(product.getName(), "")){
                product.setName(productBd.get().getName());
            }

            if (Objects.equals(product.getDescription(), productBd.get().getDescription()) || Objects.equals(product.getDescription(), "")){
                product.setDescription(productBd.get().getDescription());
            }


            if (productBd.get().getCategory().getId()!=product.getCategory().getId()&&product.getCategory().getId()!=0){
                Optional<Category> category = categoryRepository.findByIdAndActiveTrue(product.getCategory().getId());
                if(category.isPresent()){
                    product.setCategory(category.get());
                    productRepository.save(product);
                    return ResponseEntity.ok(product);
                } else {
                    ResponseModel responseModel = new ResponseModel(
                            404,
                            "Não foi possível encontar a categoria (id:" + product.getCategory().getId() + ") informada!"
                    );
                    return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
                }
            } else {
                product.setCategory(productBd.get().getCategory());
                productRepository.save(product);
                return ResponseEntity.ok(product);
            }
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Produto (id:" + idProduct +"), não foi encontrado!"
            );
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity delete(Long idProduct) {
        Optional<Product> productBd = productRepository.findByIdAndActiveTrue(idProduct);
        if (productBd.isPresent()){
            Product product = productBd.get();
            product.setActive(false);
            productRepository.save(product);

            ResponseModel responseModel = new ResponseModel(
                    200,
                    "Produto deletado corretamente!"
            );
            return ResponseEntity.ok(responseModel);
        } else {
            ResponseModel responseModel = new ResponseModel(
                    404,
                    "Não foi possível deletar o produto (id:" + idProduct + "), pois o mesmo não foi encontrado!"
            );
            return new ResponseEntity(responseModel, HttpStatus.NOT_FOUND);
        }
    }


}
