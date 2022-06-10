package com.plantillabackend;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

//Tercera Clase
@Deprecated
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    // Obtener el valor de la propiedad `security.jwt.client-id` del archivo `application.properties`.
    @Value("${security.jwt.client-id}")
    private String clientId;

    // Obtener el valor de la propiedad `security.jwt.client-secret` del archivo
    // `application.properties`.
    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    // Obtener el valor de la propiedad `security.jwt.grant-type` del archivo `application.properties`.
    @Value("${security.jwt.grant-type}")
    private String grantType;

    // Obtener el valor de la propiedad `security.jwt.scope-read` del archivo `application.properties`.
    @Value("${security.jwt.scope-read}")
    private String scopeRead;

    // Obtener el valor de la propiedad `security.jwt.scope-write` del archivo
    // `application.properties`.
    @Value("${security.jwt.scope-write}")
    private String scopeWrite = "write";

    // Una forma de obtener el valor de una propiedad del archivo application.properties.
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    /**
     * La función anterior se utiliza para configurar el servicio de detalles del cliente
     * 
     * @param configurer ClientDetailsServiceConfigurer
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.inMemory().withClient(clientId).secret(bcrypt.encode(clientSecret)).authorizedGrantTypes(grantType) // ,
                                                                                                                       // "refresh_token"
                .scopes(scopeRead, scopeWrite).resourceIds(resourceIds).accessTokenValiditySeconds(1000)
                .refreshTokenValiditySeconds(0);
    }

   /**
    * Esta función se usa para configurar los puntos finales del servidor de autorización, que se usa
    * para administrar y verificar los tokens.
    * 
    * @param endpoints AuthorizationServerEndpointsConfigurer se utiliza para configurar los puntos
    * finales expuestos por el servidor de autorización.
    */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter).tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager);
    }
}
