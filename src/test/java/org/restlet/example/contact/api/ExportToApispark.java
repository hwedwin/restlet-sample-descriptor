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

package org.restlet.example.contact.api;

import org.restlet.ext.apispark.Introspector;

/**
 * @author Manuel Boillod
 */
public class ExportToApispark {

    /**
     * Exports the web API as a Descriptor cell to APISpark.
     */
    public static void main(String[] args) throws Exception {
        exportWebApi_asDescriptor_toApiSpark();
    }

    /**
     * Prints the Introspector help
     */
    public static void printHelp() throws Exception {
        Introspector.main(new String[] { "--help", });
    }

    /**
     * Exports the web API as a Descriptor cell to APISpark.
     */
    public static void exportWebApi_asDescriptor_toApiSpark() throws Exception {
        Introspector.main(new String[] {
                "-S",
                "http://localhost:9999",
                // TODO Enter your username (from your APISpark account), or use
                // the "apispark.login" system property
                "-u",
                System.getProperty("apispark.login", "YOUR USERNAME"),
                // TODO Enter your secret key (from your APISpark account), or
                // use the "apispark.password" system property
                "-p",
                System.getProperty("apispark.password", "YOUR PASSWORD"),
                "--create-descriptor",
                "org.restlet.example.contact.api.ContactsApplication" });
    }
}