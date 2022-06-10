package com.plantillabackend;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

//Cuarta clase, clase de apoyo
public class AuthException implements AuthenticationEntryPoint {

    /**
     * Toma la solicitud, la respuesta y la excepción que se lanzó, y luego escribe una respuesta JSON
     * en el objeto de respuesta.
     * 
     * @param request El objeto de la solicitud.
     * @param response El objeto de respuesta que se enviará al cliente.
     * @param arg2 La excepción que se lanzó para rechazar la solicitud.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
            throws IOException, ServletException {
        final Map<String, Object> mapException = new HashMap<>();

        mapException.put("error", "401");
        mapException.put("message", "No estas autorizado para acceder a este recurso");
        mapException.put("exception", "No autorizado");
        mapException.put("path", request.getServletPath());
        mapException.put("timestamp", LocalDateTime.now());

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), mapException);
    }
    
}
