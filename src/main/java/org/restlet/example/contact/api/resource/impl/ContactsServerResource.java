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
import org.restlet.example.contact.api.core.util.ResourceUtils;
import org.restlet.example.contact.api.model.Contact;
import org.restlet.example.contact.api.persistence.ContactPersistence;
import org.restlet.example.contact.api.representation.ContactList;
import org.restlet.example.contact.api.resource.ContactsResource;
import org.restlet.resource.ServerResource;

/**
 * Represents a list of {@link Contact}.<br>
 * The default constructor sets some basic part of the resource's documentation:
 * its name and description. The method are automatically documented by
 * introspecting their signature (return type, parameter and "throws" clause).
 * 
 * @author Manuel Boillod
 */
public class ContactsServerResource extends ServerResource implements
        ContactsResource {

    /**
     * Constructor, documents the name and description of the resource.
     */
    public ContactsServerResource() {
        setName("Contacts");
        setDescription("Contact list resource");
    }

    @Override
    public ContactList getContacts() {
        ResourceUtils.checkRoles(this, ContactsApplication.ROLE_USER);
        return new ContactList(ContactPersistence.INSTANCE.getContacts());
    }

    @Override
    public Contact addContact(Contact contact) throws BadEntityException {
        ResourceUtils.checkRoles(this, ContactsApplication.ROLE_USER);
        ResourceUtils.notNull(contact);
        contact.validate();
        return ContactPersistence.INSTANCE.addContact(contact);
    }
}
