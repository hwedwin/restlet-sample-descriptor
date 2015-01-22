package org.restlet.example.contact.api;

import java.util.logging.Logger;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.example.contact.api.persistence.Persistence;
import org.restlet.example.contact.api.resource.impl.CompaniesServerResource;
import org.restlet.example.contact.api.resource.impl.CompanyServerResource;
import org.restlet.example.contact.api.resource.impl.ContactServerResource;
import org.restlet.example.contact.api.resource.impl.ContactsServerResource;
import org.restlet.example.contact.api.resource.impl.RootResource;
import org.restlet.ext.swagger.Swagger2SpecificationRestlet;
import org.restlet.ext.swagger.SwaggerSpecificationRestlet;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MemoryRealm;
import org.restlet.security.Role;
import org.restlet.security.User;

/**
 * An application can be seen as a set of resources. This application documents
 * its name and description, and let resources describe their supported set of
 * methods, representations, etc.<br>
 * In addition, this application is able to generate automatically, by
 * introspection, several kind of documentations:
 * <ul>
 * <li>one pushed to the apispark platform, once you own an account. This
 * documentation is editable, and allows to generate client SDKs, server
 * skeleton</li>
 * <li>a Swagger 1.2 documentation</li>
 * <li>a Swagger 2.0 documentation</li>
 * </ul>
 * 
 * @author Manuel Boillod
 */
public class ContactsApplication extends Application {

    public static final String ROLE_ADMIN = "admin";

    public static final String ROLE_USER = "user";

    /**
     * Starts the web application.
     */
    public static void main(String[] args) throws Exception {
        Logger logger = Engine.getLogger(ContactsApplication.class);

        logger.info("Contacts application starting...");

        // create data
        Persistence.populate();

        // launch server
        Component component = new Component();
        component.setName("contact application");
        component.getServers().add(Protocol.HTTP, 8000);
        component.getDefaultHost().attach(new ContactsApplication());
        component.start();

        logger.info("Contacts application started on port 8000");
        logger.info("URL: http://localhost:8000/");
    }

    /**
     * Constructor. Let us define the name and a description of the application.
     */
    public ContactsApplication() {
        setName("Contacts API application");
        setDescription("Full description of the Contacts API");

        // roles are defined at the level of the application, and are used to
        // check authorization, once the current request is authenticated.
        getRoles().add(new Role(this, ROLE_ADMIN));
        getRoles().add(new Role(this, ROLE_USER));
    }

    @Override
    public Restlet createInboundRoot() {

        // define two sets of resources: one is public, the other is behind a
        // security filter.
        Router publicRouter = publicResources();
        ChallengeAuthenticator apiGuard = createApiGuard();
        Router privateRouter = privateResources();

        // chain restlet components
        publicRouter.attachDefault(apiGuard);
        apiGuard.setNext(privateRouter);

        return publicRouter;
    }

    /**
     * Creates the public resources.
     * 
     * @return
     */
    public Router publicResources() {
        Router router = new Router();

        router.attach("/", RootResource.class);

        // Attach Swagger Specifications
        attachSwaggerSpecification1(router);
        attachSwaggerSpecification2(router);
        return router;
    }

    /**
     * Defines the of resources of the Contacts API. This API requires
     * authentication and sufficient permissions.
     * 
     * @return
     */
    public Router privateResources() {
        Router router = new Router();

        // Attach Resources
        router.attach("/contacts", ContactsServerResource.class);
        router.attach("/contacts/{contactId}", ContactServerResource.class);

        router.attach("/companies", CompaniesServerResource.class);
        router.attach("/companies/{companyId}", CompanyServerResource.class);
        return router;
    }

    /**
     * Returns the filter that handles security. It is based on BASIC HTTP
     * authentication. For a matter of simplicity, the set of login/password are
     * stored in memory.
     * 
     * @return
     */
    private ChallengeAuthenticator createApiGuard() {

        ChallengeAuthenticator apiGuard = new ChallengeAuthenticator(
                getContext(), ChallengeScheme.HTTP_BASIC, "realm");

        // Create in-memory users and roles.
        MemoryRealm realm = new MemoryRealm();

        User admin = new User("admin", "password");
        realm.getUsers().add(admin);
        realm.map(admin, Role.get(this, ROLE_ADMIN));
        realm.map(admin, Role.get(this, ROLE_USER));

        User user = new User("user", "password");
        realm.getUsers().add(user);
        realm.map(user, Role.get(this, ROLE_USER));

        // - Verifier: checks authentication
        // - Enroler: used later in resources to check authorization (roles)
        apiGuard.setVerifier(realm.getVerifier());
        apiGuard.setEnroler(realm.getEnroler());

        // NB:
        // - provide your own authentication checks by extending SecretVerifier
        // or LocalVerifier classes.
        // - extend the Enroler class and customize the way you assign roles for
        // an authenticated user.

        return apiGuard;
    }

    /**
     * Adds the "/api-docs" path to the given router and attaches the
     * {@link Restlet} that computes the Swagger documentation in the format
     * defined by the swagger-spec project v1.2.
     * 
     * @param router
     *            The router to update.
     */
    private void attachSwaggerSpecification1(Router router) {
        SwaggerSpecificationRestlet swaggerSpecificationRestlet = new SwaggerSpecificationRestlet(
                this);
        swaggerSpecificationRestlet.setBasePath("http://myapp.com/api/");
        swaggerSpecificationRestlet.attach(router);
    }

    /**
     * Adds the "/swagger.json" path to the given router and attaches the
     * {@link Restlet} that computes the Swagger documentation in the format
     * defined by the swagger-spec project v2.0.
     * 
     * @param router
     *            The router to update.
     */
    private void attachSwaggerSpecification2(Router router) {
        Swagger2SpecificationRestlet restlet = new Swagger2SpecificationRestlet(
                this);
        restlet.setBasePath("http://myapp.com/api/");
        restlet.attach(router);
    }
}
