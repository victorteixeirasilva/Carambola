package com.carambola.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

/**
 * @description Classe responsavel por criar a entidade no user no banco de dados, dentro dessa classe estão agrupados
 * todos os atributos dos tipos de funcionários diferentes (cliente, estabelecimento, funcionario)
 * cujo quais vamos separar as permições por roles, e para separar eles em cadastros por forms.
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
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USER_TEL", unique = true, nullable = false)
    private long telephone;
    @Column(name = "USER_EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;
    @Column(name = "USER_NAME")
    private String name;
    @Column(name = "USER_DATA_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "USER_CPF")
    private String cpf;
    @Column(name = "USER_HOUSE_NUMBER")
    private String houseNumber;
    @ManyToOne
    @JoinColumn(name = "USER_ADDRESS")
    private Address address;
    @OneToOne
    private FormOfPayment formOfPayment;
    @Column(name = "USER_ASSESSMENT")
    private double Assessment;
    @Column(name = "USER_CNPJ")
    private String cnpj;





}

