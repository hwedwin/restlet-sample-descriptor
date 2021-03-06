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

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.wordnik.swagger.annotations.ApiOperation;

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

    @ApiOperation("readme")
    @Get("txt")
    public Representation getReadme() {
        return new ClientResource(
                "clap:///org/restlet/example/contact/api/readme.txt").get();
    }

}
