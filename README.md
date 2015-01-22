
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
mvn test -Pexport-to-apispark -Dexec.classpathScope="test" -Dapispark.login=<your username> -Dapispark.password=<your secret key>
```

### Where to customize part of the documentation
 - ContactsApplication class
 - resources described by their interface in the package org.restlet.example.contact.api.resource
 - exception serialized in the package org.restlet.example.contact.api.core.exception

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


