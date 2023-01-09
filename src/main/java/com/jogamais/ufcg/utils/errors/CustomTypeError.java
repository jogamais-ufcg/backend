package com.jogamais.ufcg.utils.errors;

public class CustomTypeError {
    private String errorMessage;

    public CustomTypeError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
