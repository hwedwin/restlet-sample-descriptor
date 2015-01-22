package org.restlet.example.contact.api.resource.impl;

import org.restlet.example.contact.api.ContactsApplication;
import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.core.util.ResourceUtils;
import org.restlet.example.contact.api.model.Company;
import org.restlet.example.contact.api.persistence.CompanyPersistence;
import org.restlet.example.contact.api.resource.CompanyResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Represents the "Company" resource. One instance of this class is created for
 * each request.
 * 
 * @author Manuel Boillod
 */
public class CompanyServerResource extends ServerResource implements
        CompanyResource {

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
}
