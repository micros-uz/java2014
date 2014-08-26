package uz.micros.jstore.config;

/*
The SecurityWebAppInitializer will automatically
register the springSecurityFilterChain Filter for every URL in
your application. If Filters are added within other
WebApplicationInitializer instances we can use @Order to
control the ordering of the Filter instances.
 */

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer{
}

