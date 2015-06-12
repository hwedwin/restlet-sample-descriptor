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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.restlet.example.contact.api.ContactsApplication;
import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.core.util.ResourceUtils;
import org.restlet.example.contact.api.model.Company;
import org.restlet.example.contact.api.persistence.CompanyPersistence;
import org.restlet.example.contact.api.resource.CompanyResource;
import org.restlet.ext.apispark.internal.introspection.DocumentedResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Represents the "Company" resource. One instance of this class is created for
 * each request.
 * 
 * @author Manuel Boillod
 */
public class CompanyServerResource extends ServerResource implements
        CompanyResource, DocumentedResource {

    private Integer companyId;

    /**
     * Invoked after the constructor, this is the place where to set up the
     * state of the resource.
     */
    @Override
    protected void doInit() throws ResourceException {
        companyId = ResourceUtils.getPathVariableAsInteger(this, "companyId");
    }

    @Override
    public Company getCompany() throws NotFoundException {
        ResourceUtils.checkRoles(this, ContactsApplication.ROLE_USER);
        Company company = CompanyPersistence.INSTANCE.getCompany(companyId);
        if (company == null) {
            throw new NotFoundException("No company with id: " + companyId);
        }
        return company;
    }

    @Override
    public Company update(Company company) throws NotFoundException,
            BadEntityException {
        ResourceUtils.checkRoles(this, ContactsApplication.ROLE_USER);
        ResourceUtils.notNull(company);
        company.validate();
        return CompanyPersistence.INSTANCE.updateCompany(companyId, company);
    }

    @Override
    public void remove() throws NotFoundException {
        ResourceUtils.checkRoles(this, ContactsApplication.ROLE_USER,
                ContactsApplication.ROLE_ADMIN);

        if (!CompanyPersistence.INSTANCE.deleteCompany(companyId)) {
            throw new NotFoundException("No company with id: " + companyId);
        }
    }

    @Override
    public List<String> getSections() {
        return new ArrayList<>(Arrays.asList("company"));
    }
}
