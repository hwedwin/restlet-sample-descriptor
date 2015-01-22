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

package org.restlet.example.contact.api.resource.impl;

import org.restlet.example.contact.api.ContactsApplication;
import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.core.util.ResourceUtils;
import org.restlet.example.contact.api.model.Contact;
import org.restlet.example.contact.api.persistence.ContactPersistence;
import org.restlet.example.contact.api.resource.ContactResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class ContactServerResource extends ServerResource implements
        ContactResource {

    private Integer contactId;

    @Override
    protected void doInit() throws ResourceException {
        contactId = ResourceUtils.getPathVariableAsInteger(this, "contactId");
    }

    @Override
    public Contact getContact() throws NotFoundException {
        ResourceUtils.checkRoles(this, ContactsApplication.ROLE_USER);
        Contact contact = ContactPersistence.INSTANCE.getContact(contactId);
        if (contact == null) {
            throw new NotFoundException("No contact with id '" + contactId
                    + "'");
        }
        return contact;
    }

    @Override
    public Contact updateContact(Contact contact) throws NotFoundException,
            BadEntityException {
        ResourceUtils.checkRoles(this, ContactsApplication.ROLE_USER);
        ResourceUtils.notNull(contact);
        contact.validate();
        return ContactPersistence.INSTANCE.updateContact(contactId, contact);
    }

    @Override
    public void deleteContact() throws NotFoundException {
        ResourceUtils.checkRoles(this, ContactsApplication.ROLE_USER,
                ContactsApplication.ROLE_ADMIN);
        boolean removed = ContactPersistence.INSTANCE.deleteContact(contactId);
        if (!removed) {
            throw new NotFoundException("No contact with id '" + contactId
                    + "'");
        }
    }
}
