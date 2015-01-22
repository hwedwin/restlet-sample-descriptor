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

package org.restlet.example.contact.api.model;

import java.util.List;

import org.restlet.example.contact.api.core.validation.ValidationErrors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.google.common.base.Strings;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Represents a Company.<br>
 * This sample code leverages several annotations in order to control the
 * serialization:
 * <ul>
 * <li>the {@link JsonRootName} annotation : the root element of the Json
 * representation will be called "Company".</li>
 * <li>the {@link JsonProperty} annotation : the name of the property in the
 * serialized representation.</li>
 * <li>the {@link JacksonXmlElementWrapper} annotation: in the XML
 * representation, the "tags" attribute is wrapped inside a "tags" element.</li>
 * <li>the {@link JacksonXmlProperty} annotation: in the XML representation, any
 * "Tag" element is marked as "tag" instead of "tags".</li>
 * </ul>
 * <br>
 * This sample code leverages several Swagger annotations in order to control
 * the documentation of the bean:
 * <ul>
 * <li>the {@link ApiModelProperty} annotation : the description of the
 * annotated field.</li>
 * </ul>
 * 
 * @author Manuel Boillod
 */
@JsonRootName("Company")
public class Company {

    private Integer id;

    private String name;

    private List<String> tags;

    @ApiModelProperty("the company id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty("the company name")
    @JsonProperty(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty("the company tags")
    public List<String> getTags() {
        return tags;
    }

    @JacksonXmlElementWrapper(localName = "tags")
    @JacksonXmlProperty(localName = "tag")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void validate() {
        ValidationErrors validationErrors = new ValidationErrors();
        if (id == null) {
            validationErrors.addFieldError("id", "This field is required");
        }
        if (Strings.isNullOrEmpty(name)) {
            validationErrors.addFieldError("name", "This field is required");
        }
        validationErrors.checkErrors("Company entity is not valid");
    }

}
