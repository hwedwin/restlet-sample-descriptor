package org.restlet.example.contact.api.resource.impl;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * This resource will be automatically documented as well even if it does not
 * rely on an interface.
 * 
 * @author Manuel Boillod
 * 
 */
public class RootResource extends ServerResource {

    public RootResource() {
        setName("Readme");
        setDescription("Readme resource");
    }

    @Get("txt")
    public Representation getReadme() {
        return new ClientResource(
                "clap:///org/restlet/example/contact/api/readme.txt").get();
    }

}
