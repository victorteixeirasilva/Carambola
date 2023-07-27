package com.carambola.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description Os atributos desse modelo foram gerados automaticamente pelo site
 * jsonschema2pojo.org. Para isso, usamos o JSON de retorno da API do ViaCEP.
 * Essa classe serve cria uma entidade no nosso banco de dados para armazenar os
 * cep já buscados, para que a API não ultrapasse o consumo da API via cep
 * e faça as buscas mais rapidamente no nosso proprio banco.
 *
 * @see <a href="https://www.jsonschema2pojo.org">jsonschema2pojo.org</a>
 * @see <a href="https://viacep.com.br">ViaCep</a>
 *
 * @author victorteixeirasilva 23/JULHO/2023
 *
 * @version 1.0
 *
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity(name = "TB_ADDRESS")
public class Address {

    @Id
    @Column(name = "ADR_CEP")
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

}