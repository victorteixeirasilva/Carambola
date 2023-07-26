package com.carambola.model.form.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class FormOfPaymentForm {
    private int securityCode;
    private String cardNumber;
    private String cardHolderName;
}
