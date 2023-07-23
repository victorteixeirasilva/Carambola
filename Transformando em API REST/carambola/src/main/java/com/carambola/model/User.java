package com.carambola.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

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
    @Column(name = "USE_ID")
    private Long id;
    @Column(name = "USU_TEL", unique = true, nullable = false)
    private long telephone;
    @Email
    @Column(name = "USU_EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "USU_PASSWORD", nullable = false)
    private String password;
    @Column(name = "USU_NAME")
    private String name;
    @Column(name = "USU_DATA_OF_BIRTH")
    private Date  dateOfBirth;
    @CPF
    @Column(name = "USU_CPF")
    private long cpf;
    @Column(name = "USU_HOUSE_NUMBER")
    private String houseNumber;
//    @Column(name = "USU_ADDRESS")
    @ManyToOne
    @JoinColumn(name = "USU_ADDRESS")
    private Address address;
//    @Column(name = "USU_FORM_OF_PAYMENT")
    @OneToOne
    private FormOfPayment formOfPayment;
    @Column(name = "USU_ASSESSMENT")
    private double Assessment;
    @CNPJ
    @Column(name = "USU_CNPJ")
    private Long cnpj;





}

