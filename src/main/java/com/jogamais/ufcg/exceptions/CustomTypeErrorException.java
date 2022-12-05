package com.jogamais.ufcg.exceptions;

public class CustomTypeErrorException {
    private String errorMessage;

    public CustomTypeErrorException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return  errorMessage;
    }
}
