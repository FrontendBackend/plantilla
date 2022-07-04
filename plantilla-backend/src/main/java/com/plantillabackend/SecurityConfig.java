package com.plantillabackend;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//Primera Clase
@Deprecated
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Obtener el valor de la propiedad `security.signing-key` del archivo `application.properties`.
    @Value("${security.signing-key}")
    private String signingKey;

    // Obtener el valor de la propiedad `security.encoding-strength` del archivo
    // `application.properties`.
    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    // Obtener el valor de la propiedad `security.security-realm` del archivo `application.properties`.
    @Value("${security.security-realm}")
    private String securityRealm;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    /**
     * BCryptPasswordEncoder es una clase en Spring Security que implementa la interfaz
     * PasswordEncoder.
     * 
     * La interfaz de PasswordEncoder es una interfaz de marcador.
     * 
     * Una interfaz de marcador es una interfaz que no contiene métodos ni constantes, pero se usa para
     * indicar que una clase implementa un comportamiento particular.
     * 
     * La interfaz PasswordEncoder se usa para indicar que una clase se puede usar para codificar
     * contraseñas.
     * 
     * La clase BCryptPasswordEncoder se usa para codificar contraseñas usando el algoritmo BCrypt.
     * 
     * La clase BCryptPasswordEncoder es una implementación concreta de la interfaz PasswordEncoder.
     * 
     * La clase BCryptPasswordEncoder es una clase de Spring Security.
     * 
     * La clase BCryptPasswordEncoder es una clase Spring Security que implementa la interfaz
     * PasswordEncoder.
     * 
     * La clase BCryptPasswordEncoder es una clase Spring Security que implementa la interfaz
     * PasswordEncoder.
     * 
     * El codificador BCryptPassword
     * 
     * @return Un objeto BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEnconder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    /**
     * La función authenticationManager() se utiliza para autenticar las credenciales del usuario
     * 
     * @return Se está devolviendo el AuthenticationManager.
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * "Esta función es llamada por el marco Spring Security para configurar el administrador de
     * autenticación.
     * El administrador de autenticación es responsable de autenticar a los usuarios.
     * El administrador de autenticación está configurado para usar el servicio de detalles de usuario
     * y el codificador de contraseñas".
     * 
     * @param auth AutenticaciónManagerBuilder
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .realmName(securityRealm)
                .and()
                .csrf()
                .disable();
    }

    /**
     * JwtAccessTokenConverter es una clase que se utiliza para convertir el token de acceso en un
     * token JWT
     * 
     * @return Un objeto JwtAccessTokenConverter.
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    /**
     * La función tokenStore() se utiliza para almacenar el token en la base de datos.
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter()); //EN MEMORIA
        // return new JdbcTokenStore(this.dataSource); // EN BASE DE DATOS // Es permitido descomentar las tablas del modelo TblOauthAccessToken y TblOauthRefreshToken
    }

    /**
     * Esta función se utiliza para crear un nuevo token cuando un usuario inicia sesión y para
     * actualizar el token cuando el usuario inicia sesión.
     * 
     * @return Un objeto DefaultTokenServices.
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setReuseRefreshToken(false);
        return defaultTokenServices;
    }
}
