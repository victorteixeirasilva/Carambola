package com.carambola.exception;

public class IncorrectFileExtensionException extends RuntimeException{
    public IncorrectFileExtensionException(String errorMessage) {
        super(errorMessage);
    }
}
