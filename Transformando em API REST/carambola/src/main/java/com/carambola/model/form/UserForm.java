package com.carambola.model.form;

import com.carambola.model.Address;
import com.carambola.model.FormOfPayment;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserForm {
    private String name;
    @CPF(message = "'${validatedValue} é invalido!")
    private String cpf;
    @Email(message = "'${validatedValue} é invalido!")
    private String email;
    private String password;
    private long telephone;
    private LocalDate dateOfBirth;
    private String cep;
    private String houseNumber;

}
