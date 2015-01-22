package org.restlet.example.contact.api.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.restlet.example.contact.api.core.exception.BadParameterException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.model.Contact;

/**
 * Persistence layer for handling contacts.
 * 
 * @author Manuel Boillod
 */
public class ContactPersistence {

    public static ContactPersistence INSTANCE = new ContactPersistence();

    private AtomicInteger idGenerator = new AtomicInteger();

    private Map<Integer, Contact> contacts = Collections
            .synchronizedMap(new LinkedHashMap<Integer, Contact>());

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts.values());
    }

    public Contact getContact(Integer contactId) {
        return contacts.get(contactId);
    }

    /**
     * Adds a contact; it checks the company does not already exists.
     * 
     * @param contact
     *            The contact to add.
     * @return The added contact with its identifier.
     * @throws BadParameterException
     *             In case the contact already exists.
     */
    public Contact addContact(Contact contact) throws BadParameterException {
        if (contact.getId() == null) {
            contact.setId(idGenerator.incrementAndGet());
        } else {
            Contact existing = getContact(contact.getId());
            if (existing != null) {
                throw new BadParameterException("Contact with id '"
                        + contact.getId() + "' already exists");
            }
        }
        contacts.put(contact.getId(), contact);
        return contact;
    }

    /**
     * Updates the given contact; it checks that the contact already exists.
     * 
     * @param contactId
     *            The identifier of the contact to update.
     * @param contact
     *            The new contact.
     * @return The updated contact.
     * @throws NotFoundException
     *             In case the contact does not exist.
     */
    public Contact updateContact(Integer contactId, Contact contact)
            throws NotFoundException {
        Contact existing = getContact(contactId);
        if (existing == null) {
            throw new NotFoundException("No contact with id '" + contactId
                    + "'");
        }
        contacts.put(contactId, contact);
        return contact;
    }


    /**
     * Removes a contact, and indicates if the contact has really been removed.
     * 
     * @param contactId
     * @return True if the contact has been removed.
     */
    public boolean deleteContact(Integer contactId) {
        Contact existing = contacts.remove(contactId);
        return existing != null;
    }
}
