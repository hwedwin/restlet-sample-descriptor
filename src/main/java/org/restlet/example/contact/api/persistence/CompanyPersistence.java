package org.restlet.example.contact.api.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.restlet.example.contact.api.core.exception.BadParameterException;
import org.restlet.example.contact.api.core.exception.NotFoundException;
import org.restlet.example.contact.api.model.Company;

/**
 * Persistence layer for handling companies.
 * 
 * @author Manuel Boillod
 */
public class CompanyPersistence {

    public static CompanyPersistence INSTANCE = new CompanyPersistence();

    private AtomicInteger idGenerator = new AtomicInteger();

    private Map<Integer, Company> companies = Collections
            .synchronizedMap(new LinkedHashMap<Integer, Company>());

    public List<Company> getCompanies() {
        return new ArrayList<>(companies.values());
    }

    public Company getCompany(Integer companyId) {
        return companies.get(companyId);
    }

    /**
     * Adds a company; it checks the company does not already exists.
     * 
     * @param company
     *            The company to add.
     * @return The added company with its identifier.
     * @throws BadParameterException
     *             In case the company already exists.
     */
    public Company addCompany(Company company) throws BadParameterException {
        if (company.getId() == null) {
            company.setId(idGenerator.incrementAndGet());
        } else {
            Company existing = getCompany(company.getId());
            if (existing != null) {
                throw new BadParameterException("Company with id '"
                        + company.getId() + "' already exists");
            }
        }
        companies.put(company.getId(), company);
        return company;
    }

    /**
     * Updates the given company; it checks that the company already exists.
     * 
     * @param companyId
     *            The identifier of the company to update.
     * @param company
     *            The new company.
     * @return The updated company.
     * @throws NotFoundException
     *             In case the company does not exist.
     */
    public Company updateCompany(Integer companyId, Company company)
            throws NotFoundException {
        Company existing = getCompany(companyId);
        if (existing == null) {
            throw new NotFoundException("No company with id '" + companyId
                    + "'");
        }

        companies.put(companyId, company);
        return company;
    }

    /**
     * Removes a company, and indicates if the company has really been removed.
     * 
     * @param companyId
     * @return True if the company has been removed.
     */
    public boolean deleteCompany(Integer companyId) {
        Company existing = companies.remove(companyId);
        return existing != null;
    }
}
