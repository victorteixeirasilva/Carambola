package com.carambola.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description Classe responsavel por amazenar informações de pagamento, como número
 * bandeira, código de segurança, do cartão do usuário.
 *
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
@Table(name = "TB_FORM_OF_PAYMENT")
public class FormOfPayment {
    @Id
    @Column(name = "form_of_payment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int securityCode;
    private String cardNumber;
    private String cardHolderName;
    private String cardFlag;

    private String checkCardFlag(String number1IdIssuer, String number2IdIssuer) {
        if(number2IdIssuer.equals("37")) {
            setCardFlag("AMERICAN EXPRESS");
            return "AMERICAN EXPRESS";
        } else if (number2IdIssuer.equals("35")) {
            setCardFlag("JCB");
            return "JCB";
        } else if (number1IdIssuer.equals("4")) {
            setCardFlag("VISA");
            return "VISA";
        } else if (number1IdIssuer.equals("5")) {
            setCardFlag("MASTER CARD");
            return "MASTER CARD";
        } else if (number1IdIssuer.equals("6")) {
            setCardFlag("DISCOVER");
            return "DISCOVER";
        } else {
            setCardFlag("Bandeira desconhecida");
            return "Bandeira desconhecida";
        }
    }

    private boolean cardValidityCheck(String cardNumber) {
        int sumEven = 0;
        int oddSum = 0;
        int aux;

        //PARES
        for(int j = cardNumber.length()-2;j >= 0;j = j-2) {
            aux = Integer.parseInt(cardNumber.charAt(j)+"");
            System.out.println("digito: "+aux+" dobro: "+(aux*2)+" soma digitos: "+ sumDigitsCard(aux*2));
            sumEven = sumEven + sumDigitsCard(aux*2);
        }
        System.out.println("Soma par: "+sumEven);

        //IMPARES
        for(int i = cardNumber.length()-1; i>=0; i=i-2) {
            aux = Integer.parseInt(cardNumber.charAt(i)+"");
            System.out.println("digito: "+aux+" soma digitos: "+aux);
            oddSum = oddSum + aux;
        }
        System.out.println("Soma impar: "+oddSum);

        System.out.println("Soma total pares e impares: "+(sumEven+oddSum));

        if((sumEven+oddSum)%10==0) {
            return true;
        } else {
            return false;
        }
    }

    private int sumDigitsCard(int cardNumber) {
        if(cardNumber<9) {
            return cardNumber;
        } else {
            return cardNumber%10 + 1;
        }

    }

    public boolean validateCard(String cardNumber) {
        if((cardNumber.length() >= 13)&&(cardNumber.length() <= 16)) {
            boolean flag = cardValidityCheck(cardNumber);

            if(flag) {
                System.out.println("Cartão Válido!");
                this.cardFlag = checkCardFlag(cardNumber.substring(0,1),cardNumber.substring(0,2));
                System.out.println("Cartão Válido!"
                        + "\n\nNúmero do cartão: "+cardNumber
                        + "\n\nBandeira do cartão:"+this.cardFlag);
                return true;
            } else {
                System.out.println("Cartão Inválido!");
                return false;
            }
        } else {
            System.out.println("Número de cartão inválido!");
            return false;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(String cardFlag) {
        this.cardFlag = cardFlag;
    }
}