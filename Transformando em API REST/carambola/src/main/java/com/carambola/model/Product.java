package com.carambola.model;

import com.carambola.model.form.establishment.ProductForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description Classe responsavel por criar a entidade product no banco de dados, dentro dessa classe estão
 * os atributos dos produtos.
 *
 * @author victorteixeirasilva 23/julho/2023
 * @version 1.0
 *
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "TB_PRODUCT")
public class Product {
    @Id
    @Column(name = "PRO_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
    private String name;
    private String description;
    private boolean haveStock;
    private Double value;
    private boolean active = true;

    public Product(ProductForm productForm) {

        Category categoryForm = new Category();
        categoryForm.setId(productForm.getIdCategory());

        this.category = categoryForm;
        this.name = productForm.getName();
        this.description = productForm.getDescription();
        this.haveStock = productForm.isHaveStock();
        this.value = productForm.getValue();

    }
}
