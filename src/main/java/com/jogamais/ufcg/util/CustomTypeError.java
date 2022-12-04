package com.jogamais.ufcg.util;

public class CustomTypeError {
    private String errorMessage;

    public CustomTypeError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return  errorMessage;
    }
}
