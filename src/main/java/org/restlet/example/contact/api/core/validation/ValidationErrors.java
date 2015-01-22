package org.restlet.example.contact.api.core.validation;

import java.util.ArrayList;
import java.util.List;

import org.restlet.example.contact.api.core.exception.BadEntityException;

/**
 * Lists of global and fields errors.
 * 
 * @author Manuel Boillod
 */
public class ValidationErrors {

    private List<String> globalMessages = new ArrayList<>();

    private List<FieldError> fieldErrors = new ArrayList<>();

    public List<String> getGlobalMessages() {
        return globalMessages;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void addGlobalMessage(String globalMessage) {
        globalMessages.add(globalMessage);
    }

    public void addFieldError(String field, String message) {
        addFieldError(new FieldError(field, message));
    }

    public void addFieldError(FieldError fieldError) {
        fieldErrors.add(fieldError);
    }

    public void checkErrors(String message) {
        if (!globalMessages.isEmpty() || !fieldErrors.isEmpty()) {
            throw new BadEntityException(message, this);
        }
    }
}
