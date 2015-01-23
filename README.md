
# Download and run
The source code is available in git repository:

```
git clone https://github.com/restlet/restlet-sample-descriptors.git
```

The main method in located in the ContactsApplication class.

#Documentation

## this sample project is able to generate several kind of documentation

- one pushed to the apispark platform, once you own an account. This documentation is editable, and allows to generate client SDKs, server skeleton.
- a Swagger 1.2 documentation available online
- a Swagger 2.0 documentation available online

##Generation of editable documentation in APISpark
1. Sign up to APISpark (https://apispark.restlet.com), then retrieve your credentials in the "My Account' section.

2. Run the introspection code

```
mvn test -Pexport-to-apispark -Dapispark.username=<your username> -Dapispark.secretkey=<your secret key>
```

### Where to customize part of the documentation
 - ContactsApplication class
 - resources described by their interface in the package org.restlet.example.contact.api.resource
 - exception serialized in the package org.restlet.example.contact.api.core.exception

####  Customization of the application
 - in the constructor, `setName` and `setDescription`
 
####  Customization of the resources, thanks to the Restlet Framework
 - in the constructor, `setName` and `setDescription`

####  Customization of a resource, thanks to Swagger annotations
 - use the `@Api` annotation either on the annotated interface, or in the implementation class:

```java
@Api(value = "Companies", description = "Company list resource")
```

####  Customization of a method, thanks to Swagger annotations
 - use the `@ApiOperation` annotation:

```java
@ApiOperation(value = "list the companies", tags = "company")
```

 - use the `@ApiResponses` annotation, only for online Swagger documentation. Status and representation are deduced from the signature of the method:

```java
    @ApiResponses({
            @ApiResponse(code = 200, message = "the added company"),
            @ApiResponse(code = 422, message = "company not valid", response = BadEntityException.class)
    })
```

####  Customization of a bean, thanks to Jackson annotations in order to control serialization/documentation, only if you leverage the Jackson extension
 - use the `@JsonInclude(JsonInclude.Include.NON_EMPTY)` annotation to exclude empty or null attributes (cf class BadEntityException)
 - use the `@JsonRootName` annotation: defines the name of the root element of the Json (cf class CompanyList)
 - use the `@JsonProperty` annotation : the name of the property in the serialized representation (cf class Company).
 - use the `@JacksonXmlRootElement` annotation: in the XML representation, sets the name of the root element.
 - use the `@link JacksonXmlElementWrapper` annotation: in the XML representation, the "tags" attribute is wrapped inside a "tags" element (cf class CompanyList).
 - use the `@link JacksonXmlProperty` annotation: in the XML representation, any "Tag" element is marked as "tag" instead of "tags" (cf class CompanyList).

####  Customization of a bean, thanks to Swagger annotations in order to control the documentation of the bean:
 - use the `@ApiModelProperty` annotation : the description of the annotated field.

##List of sample commands available.

```
# list of companies in distinct formats
curl http://localhost:8000/companies
curl http://localhost:8000/companies -H 'accept: application/xml'
curl http://localhost:8000/companies -H 'accept: application/x-yaml'
curl http://localhost:8000/companies?media=yaml'

# representation of a company in distinct formats
curl http://localhost:8000/companies/1
curl http://localhost:8000/companies/1?media=xml'
curl http://localhost:8000/companies/1 -H 'accept: application/x-yaml'

# list of contacts in distinct formats
curl http://localhost:8000/contacts
curl http://localhost:8000/contacts?media=xml'
curl http://localhost:8000/contacts -H 'accept: application/x-yaml'

# representation of a contact in distinct formats
curl http://localhost:8000/contacts/1
curl http://localhost:8000/contacts/1 -H 'accept: application/xml'
curl http://localhost:8000/contacts/1?media=yaml'

# Swagger 1 documentation, available in Swagger UI.
curl http://localhost:8000/api-docs
curl http://localhost:8000/api-docs/companies
curl http://localhost:8000/api-docs/contacts

# Swagger 2.0 documentation, available in Swagger UI.
curl http://localhost:8000/swagger.json
```


