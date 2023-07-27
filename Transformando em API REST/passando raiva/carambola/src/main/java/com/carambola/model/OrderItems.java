package com.carambola.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description Classe responsavel por criar a entidade order items no banco de dados.
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
@Table(name = "tb_order_items")
public class OrderItems {
    @EmbeddedId
    private OrdemItemsId ordemItemsId;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
    private Double amount;





}