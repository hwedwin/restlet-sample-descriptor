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
