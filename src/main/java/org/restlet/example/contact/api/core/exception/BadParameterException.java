package org.restlet.example.contact.api.core.exception;

import org.restlet.resource.Status;

/**
 * Sent back to client when an incoming entity is invalid. It defines a
 * "message" property and leads to set the HTTP response status to 400
 * ("bad request") thanks to the {@link Status} annotation.
 * 
 * @author Manuel Boillod
 */
@Status(400)
public class BadParameterException extends BusinessException {

    public BadParameterException(String message) {
        super(400, message);
    }
}
