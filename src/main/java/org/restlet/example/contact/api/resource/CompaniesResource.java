package org.restlet.example.contact.api.resource;

import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.model.Company;
import org.restlet.example.contact.api.representation.CompanyList;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Status;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * Defines the contract of the "List of companies" resource.<br>
 * The contract is formally based on a Java interface that is to say a set of
 * Java methods annotated with Restlet annotations (in this case {@link Get} and
 * {@link Post}).<br>
 * Such interface eases the development of client code based on Restlet
 * Framework.<br>
 * By default, the signature of the methods help to generate documentation:
 * <ul>
 * <li>the name of the HTTP method, using the Restlet based annotations (in this
 * case {@link Get} and, {@link Post})</li>
 * <li>the return type, if any, describes the entity sent back in case of
 * success.</li>
 * <li>the single parameter, if any, describes the entity sent in the request.</li>
 * <li>the exceptions declared in the "throws" clause, if any, define the
 * behavior of the method in case of errors. If annotated with the
 * {@link Status}, each exception is serialized and sets the status code of the
 * HTTP response.</li>
 * </ul>
 * Swagger annotations help to enrich it:
 * <ul>
 * <li>{@link Api} annotation overrides the name and description of this
 * resource.</li>
 * <li>{@link ApiOperation} annotation set the name and description of a method.
 * </li>
 * <li>{@link ApiResponses} annotation overrides the representations sent in
 * case of success or errors.</li>
 * </ul>
 * 
 * @author Manuel Boillod
 */
@Api(value = "Companies", description = "Company list resource")
public interface CompaniesResource {

    @ApiOperation(value = "list the companies", tags = "company")
    @ApiResponses({ @ApiResponse(code = 200, message = "the list of companies"), })
    @Get
    CompanyList getCompanies();

    @ApiOperation(value = "add a company", tags = "company")
    @ApiResponses({
            @ApiResponse(code = 200, message = "the added company"),
            @ApiResponse(code = 422, message = "company not valid", response = BadEntityException.class) })
    @Post
    Company addCompany(Company company) throws BadEntityException;

}