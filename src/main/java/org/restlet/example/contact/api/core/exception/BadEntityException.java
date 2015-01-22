package org.restlet.example.contact.api.core.exception;

import java.util.List;

import org.restlet.example.contact.api.core.validation.FieldError;
import org.restlet.example.contact.api.core.validation.ValidationErrors;
import org.restlet.resource.Status;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Sent back to client when an incoming entity is invalid. It lists global
 * errors and errors detected on fields, and leads to set the HTTP response
 * status to 422 ("unprocessable entity") thanks to the {@link Status}
 * annotation.<br>
 * This sample code leverages the {@link JsonInclude} annotation in order to
 * control the serialization: only properties that are not null or non-empty are
 * written out.
 * 
 * @author Manuel Boillod
 */
@Status(422)
public class BadEntityException extends BusinessException {

    private List<String> globalMessages;

    private List<FieldError> fieldErrors;

    public BadEntityException(String message) {
        super(422, message);
    }

    public BadEntityException(String message, ValidationErrors validationErrors) {
        this(message);
        this.globalMessages = validationErrors.getGlobalMessages();
        this.fieldErrors = validationErrors.getFieldErrors();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> getGlobalMessages() {
        return globalMessages;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
