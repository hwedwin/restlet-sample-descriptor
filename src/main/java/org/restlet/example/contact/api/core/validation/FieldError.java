package org.restlet.example.contact.api.core.validation;

/**
 * Represents an error located on an entity field.
 * 
 * @author Manuel Boillod
 */
public class FieldError {

    private String field;

    private String message;

    public FieldError() {
    }

    public FieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
