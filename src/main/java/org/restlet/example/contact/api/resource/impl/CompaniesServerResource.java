package org.restlet.example.contact.api.resource.impl;

import org.restlet.example.contact.api.ContactsApplication;
import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.util.ResourceUtils;
import org.restlet.example.contact.api.model.Company;
import org.restlet.example.contact.api.persistence.CompanyPersistence;
import org.restlet.example.contact.api.representation.CompanyList;
import org.restlet.example.contact.api.resource.CompaniesResource;
import org.restlet.resource.ServerResource;

public class CompaniesServerResource extends ServerResource implements
        CompaniesResource {

    @Override
    public CompanyList getCompanies() {
        ResourceUtils.checkRoles(this, ContactsApplication.ROLE_USER);
        return new CompanyList(CompanyPersistence.INSTANCE.getCompanies());
    }

    @Override
    public Company addCompany(Company company) throws BadEntityException {
        ResourceUtils.checkRoles(this, ContactsApplication.ROLE_USER);
        ResourceUtils.notNull(company);
        company.validate();
        return CompanyPersistence.INSTANCE.addCompany(company);
    }
}
