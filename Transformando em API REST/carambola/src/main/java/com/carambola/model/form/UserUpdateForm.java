package com.carambola.model.form;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserUpdateForm {
    private String name;
    @Email(message = "'${validatedValue} é invalido!")
    private String email;
    private String password;
    private long telephone;
    private LocalDate dateOfBirth;
    private String cep;
    private String houseNumber;
}
