package com.carambola.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description Classe responsavel por criar a entidade category no banco de dados, dentro dessa classe estão
 * os atributos dos categorias.
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
@Table(name = "TB_CATEGORY")
public class Category {
    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "CATALOG_ID")
    private Catalog catalog;
    @ManyToOne
    @JoinColumn(name = "parent_category")
    private Category parentCategory;
    private String name;











}