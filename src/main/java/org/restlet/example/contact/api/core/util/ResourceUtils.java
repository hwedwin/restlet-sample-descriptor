/**
 * Copyright 2005-2015 Restlet
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or or EPL 1.0 (the "Licenses"). You can
 * select the license that you prefer but you may not use this file except in
 * compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://restlet.com/products/restlet-framework
 * 
 * Restlet is a registered trademark of Restlet S.A.S.
 */

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
