package org.restlet.example.contact.api.resource;

import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.model.Contact;
import org.restlet.example.contact.api.representation.ContactList;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Status;

/**
 * Defines the contract of the "List of contacts" resource.<br>
 * The contract is formally based on a Java interface that is to say a set of
 * Java methods annotated with Restlet annotations (in this case {@link Get} and
 * {@link Post}).<br>
 * Such interface eases the development of client code based on Restlet
 * Framework.<br>
 * By default, the signature of the methods help to generate documentation:
 * <ul>
 * <li>the name of the HTTP method using the Restlet based annotations (in this
 * case {@link Get} and, {@link Post})</li>
 * <li>the return type, if any, describes the entity sent back in case of
 * success.</li>
 * <li>the single parameter, if any, describes the entity sent in the request.</li>
 * <li>the exceptions declared in the "throws" clause, if any, define the
 * behavior of the method in case of errors. If annotated with the
 * {@link Status}, each exception is serialized and sets the status code of the
 * HTTP response.</li>
 * </ul>
 * In this case, we have chosen to not leverage Swagger annotations, which
 * allows you to discover the level of documentation available by default.
 * 
 * @author Manuel Boillod
 */
public interface ContactsResource {

    @Get
    ContactList getContacts();

    @Post
    Contact addContact(Contact contact) throws BadEntityException;

}