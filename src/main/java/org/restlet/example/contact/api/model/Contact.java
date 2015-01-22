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

import java.util.Date;

import org.restlet.example.contact.api.core.validation.ValidationErrors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Represents a Contact.<br>
 * This sample code leverages several annotations in order to control the
 * serialization:
 * <ul>
 * <li>the {@link JsonProperty} annotation : the name of the property in the
 * serialized representation.</li>
 * <li>the {@link JsonFormat} annotation : the format applied to the current
 * value of the property (for instance in case of Date values).</li>
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
public class Contact {

    private Integer id;

    private String firstName;

    private String lastName;

    private Date birthday;

    private Integer companyId;

    @ApiModelProperty("the contact id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty("the contact first name")
    @JsonProperty(value = "first_name", required = true)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ApiModelProperty("the contact last name")
    @JsonProperty(value = "last_name", required = true)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("the contact birthday. Date pattern is 'yyyy-MM-dd'")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ApiModelProperty("the contact company")
    @JsonProperty("company_id")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void validate() {
        ValidationErrors validationErrors = new ValidationErrors();
        if (id == null) {
            validationErrors.addFieldError("id", "This field is required");
        }
        if (Strings.isNullOrEmpty(firstName)) {
            validationErrors.addFieldError("first_name",
                    "This field is required");
        }
        if (Strings.isNullOrEmpty(lastName)) {
            validationErrors.addFieldError("last_name",
                    "This field is required");
        }
        validationErrors.checkErrors("Contact entity is not valid");
    }
}
