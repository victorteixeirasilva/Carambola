package com.carambola.exception;

import lombok.Data;

@Data
public class ResponseModel {
    public ResponseModel(Integer statusCode, String mensagem) {
        this.statusCode = statusCode;
        this.mensagem = mensagem;
    }

    Integer statusCode;
    String mensagem;
}