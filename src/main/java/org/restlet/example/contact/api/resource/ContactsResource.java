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

package org.restlet.example.contact.api.resource;

import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.model.Contact;
import org.restlet.example.contact.api.representation.ContactList;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Status;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

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
 @Api(value = "Contacts", description = "Contact list resource")
public interface ContactsResource {

    @ApiOperation(value = "list the contacts", tags = "contact")
    @ApiResponses({ @ApiResponse(code = 200, message = "the list of contacts"), })
    @Get
    ContactList getContacts();

    @ApiOperation(value = "add a contact", tags = "contact")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the added contact"),
            @ApiResponse(code = 422, message = "contact not valid", response = BadEntityException.class) })
    @Post
    Contact addContact(Contact contact) throws BadEntityException;

}
