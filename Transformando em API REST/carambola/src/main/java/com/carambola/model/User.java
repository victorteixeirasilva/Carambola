package com.carambola.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "TB_USER")
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
