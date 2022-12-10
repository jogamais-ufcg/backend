package com.jogamais.ufcg.utils;

public class CustomTypeError {
    private String errorMessage;

    public CustomTypeError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
