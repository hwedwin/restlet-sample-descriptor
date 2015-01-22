package org.restlet.example.contact.api.core.exception;

import org.restlet.resource.Status;

/**
 * Sent back to client in order to customize the 404 status response
 * ("Not found") thanks to the {@link Status} annotation. The representation
 * written out contains the status (cf constructor), and the exception message.
 * 
 * @author Manuel Boillod
 */
@Status(404)
public class NotFoundException extends BusinessException {

    public NotFoundException(String message) {
        super(404, message);
    }
}
