package com.carambola.model.form.establishment;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EstablishmentForm {
    private String name;
    @CNPJ(message = "'${validatedValue} é invalido!")
    private String cnpj;
    @Email(message = "'${validatedValue} é invalido!")
    private String email;
    private String password;
    private long telephone;
    private String cep;
    private String houseNumber;

}
