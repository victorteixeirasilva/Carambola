package com.carambola.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description Classe responsavel por criar a entidade order no banco de dados, dentro dessa classe estão agrupados
 * todos os atributos das orders.
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
@Table(name = "tb_order")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    private double discountRate;
    @OneToOne
    @JoinColumn(name = "form_of_payment_id")
    private FormOfPayment formOfPayment;
    private double orderTotalAmount;
    private String status;



}