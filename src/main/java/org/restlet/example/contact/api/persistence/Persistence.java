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
