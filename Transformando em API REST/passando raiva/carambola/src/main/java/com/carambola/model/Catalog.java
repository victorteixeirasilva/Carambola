package com.carambola.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description Classe responsavel por criar a entidade catalog no banco de dados, dentro dessa classe estão
 * os atributos dos catalogos.
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
@Table(name = "TB_CATALOG")
public class Catalog {
    @Id
    @Column(name = "CATALOG_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;





}