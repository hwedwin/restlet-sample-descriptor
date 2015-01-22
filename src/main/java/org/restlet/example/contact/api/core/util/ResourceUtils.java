package org.restlet.example.contact.api.core.util;

import org.restlet.data.Status;
import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.exception.BadParameterException;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * @author Manuel Boillod
 */
public class ResourceUtils {

    public static Integer getPathVariableAsInteger(
            ServerResource serverResource, String pathVariableName)
            throws BadParameterException {
        String pathVariableValue = serverResource
                .getAttribute(pathVariableName);

        try {
            return Integer.parseInt(pathVariableValue);
        } catch (NumberFormatException e) {
            throw new BadParameterException("Path variable '"
                    + pathVariableValue + "' should be an integer.");
        }
    }

    public static void notNull(Object entity) throws BadEntityException {
        if (entity == null) {
            throw new BadEntityException("No input entity");
        }
    }

    /**
     * Indicates if the authenticated client user associated to the current
     * request is in the given roles. We don't rely on a dedicated exception,
     * and let the framework handle this as a generic error.
     * 
     * @param serverResource
     *            The current server resource.
     * @param roles
     *            The list of roles to check.
     * @throws ResourceException
     *             A {@link ResourceException} with status
     *             {@link Status#CLIENT_ERROR_FORBIDDEN}.
     */
    public static void checkRoles(ServerResource serverResource,
            String... roles) throws ResourceException {
        for (String role : roles) {
            if (!serverResource.isInRole(role)) {
                throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN);
            }
        }
    }
}
