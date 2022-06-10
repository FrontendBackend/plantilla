package com.plantillabackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

//Segunda Clase
@Deprecated
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    
    @Autowired
    private ResourceServerTokenServices tokenServices;

    // Obtener el valor de la propiedad `security.jwt.resource-ids` del archivo application.properties.
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    /**
     * Esta función se utiliza para configurar el servidor de recursos.
     * 
     * @param resources Los recursos que están protegidos por el token OAuth2.
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }

    /**
     * Dice que todos los puntos finales que comienzan con /empleados/ están protegidos por el token
     * JWT
     * 
     * @param http El objeto HttpSecurity se utiliza para configurar la seguridad de la aplicación.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(new AuthException())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()
                // .antMatchers("/v2/api-docs/**").permitAll()
                // .antMatchers("/v3/api-docs/**").permitAll()
                // .antMatchers("/empleados/**").authenticated()
                // .antMatchers("/centrosestudios/**").authenticated()
                // .antMatchers("/conyugesempleados/**").authenticated()
                // .antMatchers("/dialectos/**").authenticated()
                // .antMatchers("/estudiossuperiores/**").authenticated()
                // .antMatchers("/profesiones/**").authenticated()
                // .antMatchers("/renaes/**").authenticated()
                // .antMatchers("/ubigeos/**").authenticated()

                // .antMatchers("/menus/**").authenticated()
                .antMatchers("/tokens/anular/**").permitAll();
                // .antMatchers("/tokens/**").authenticated();

    }
}
