package com.gilasw.codingchallenge.json;

import com.gilasw.codingchallenge.exception.ParameterValidationException;

public class NotificationJson {

    private String category;
    private String message;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void validateRequiredFields() {
        validateRequiredField("category", category);
        validateRequiredField("message", message);
    }

    private void validateRequiredField(String fieldName, String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new ParameterValidationException("The "+ fieldName + " should not be empty.");
        }
    }
}
