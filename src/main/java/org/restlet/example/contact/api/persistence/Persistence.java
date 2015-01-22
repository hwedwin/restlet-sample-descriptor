package org.restlet.example.contact.api.persistence;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.restlet.example.contact.api.model.Company;
import org.restlet.example.contact.api.model.Contact;

/**
 * @author Manuel Boillod
 */
public class Persistence {

    public static void populate() throws Exception {
        Company companyRestlet = new Company();
        companyRestlet.setName("Restlet");
        companyRestlet.setTags(Arrays.asList("API", "open source"));
        CompanyPersistence.INSTANCE.addCompany(companyRestlet);

        Company companyGoogle = new Company();
        companyGoogle.setName("Google");
        companyGoogle.setTags(Arrays.asList("search", "cloud"));
        CompanyPersistence.INSTANCE.addCompany(companyGoogle);

        Contact contact = new Contact();
        contact.setFirstName("Manuel");
        contact.setLastName("Boillod");
        contact.setBirthday(new SimpleDateFormat("dd/MM/yyyy")
                .parse("29/08/1981"));
        contact.setCompanyId(companyRestlet.getId());
        ContactPersistence.INSTANCE.addContact(contact);
    }
}
