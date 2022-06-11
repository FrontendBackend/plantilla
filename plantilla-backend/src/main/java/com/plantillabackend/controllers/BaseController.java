package com.plantillabackend.controllers;

import java.net.InetAddress;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.plantillabackend.dtos.AuditoriaDTO;
import com.plantillabackend.dtos.UserAuthDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BaseController {
    
    @Autowired
    HttpServletRequest request;

    /**
     * Permite obtener el objeto de auditoría.
     * 
     * @return AuditoriaDTO
     * @throws Exception Excepción controlada
     */
    public AuditoriaDTO obtenerAuditoria() throws Exception {

        InetAddress localHost = InetAddress.getLocalHost();
        String noTerminal = localHost.getHostName();
        String terminal = localHost.getHostAddress();

        AuditoriaDTO auditoriaDTO = new AuditoriaDTO();

        String headerAuthorization = request.getHeader("authorization").replace("Bearer", "");

        UserAuthDTO userAuthDTO = this.deserializarToken(headerAuthorization);

        if (userAuthDTO != null) {
            auditoriaDTO.setIdUsuario(userAuthDTO.getId());
            auditoriaDTO.setUsername(userAuthDTO.getUser_name());
        }

        auditoriaDTO.setTerminal(noTerminal + " / " + terminal);
        auditoriaDTO.setTerminalSeg(terminal);
        auditoriaDTO.setNoTerminal(noTerminal);
        auditoriaDTO.setFecha(new Date());

        return auditoriaDTO;
    }

    /**
     * Permite deserializar el token priviamente serializado.
     * 
     * @param idToken Token que viaje entre el frontend y backend.
     * @return
     * @throws Exception
     */
    public UserAuthDTO deserializarToken(String idToken) throws Exception {

        String jwtToken = idToken;
        UserAuthDTO userAuthDTO = new UserAuthDTO();
        String body = "";

        try {
            System.out.println("------------ Decode JWT ------------");
            String[] split_string = jwtToken.split("\\.");
            String base64EncodedHeader = split_string[0];
            String base64EncodedBody = split_string[1];

            System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
            Base64 base64Url = new Base64(true);
            String header = new String(base64Url.decode(base64EncodedHeader));
            System.out.println("JWT Header : " + header);

            body = new String(base64Url.decode(base64EncodedBody));
            System.out.println("JWT Header : " + body);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            userAuthDTO = mapper.readValue(body, UserAuthDTO.class);

        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }

        return userAuthDTO;
    }
}
