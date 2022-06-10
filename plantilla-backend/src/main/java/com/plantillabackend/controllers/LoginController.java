package com.plantillabackend.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plantillabackend.models.entity.TblResetToken;
import com.plantillabackend.models.entity.TblUsuario;
import com.plantillabackend.services.LoginService;
import com.plantillabackend.services.ResetTokenService;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private LoginService service;

    @Autowired
    private ResetTokenService tokenService;

    // @Autowired
    // private EmailUtil emailUtil;

    // @PostMapping(value = "/enviarCorreo", consumes = MediaType.TEXT_PLAIN_VALUE)
    // public ResponseEntity<Integer> enviarCorreo(@RequestBody String correo) throws Exception {
    //     int rpta = 0;

    //     TblUsuario us = service.verificarNombreUsuario(correo);
    //     if (us != null && us.getIdUsuario() > 0) {
    //         TblResetToken token = new TblResetToken();
    //         token.setToken(UUID.randomUUID().toString());
    //         token.setUser(us);
    //         token.setExpiracion(10);
    //         tokenService.guardar(token);

    //         Mail mail = new Mail();
    //         mail.setFrom("email.prueba.demo@gmail.com");
    //         mail.setTo(us.getUsername());
    //         mail.setSubject("RESTABLECER CONTRASEÑA - LEGAJO");

    //         Map<String, Object> model = new HashMap<>();
    //         String url = "http://localhost:4200/recuperar/" + token.getToken();
    //         model.put("user", token.getUser().getUsername());
    //         model.put("resetUrl", url);
    //         mail.setModel(model);

    //         emailUtil.enviarMail(mail);

    //         rpta = 1;
    //     }
    //     return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    // }

    /**
     * Comprueba si el token es válido y si no está caducado
     * 
     * @param token El token que se envió al correo electrónico del usuario.
     * @return La respuesta es un objeto JSON con la siguiente estructura:
     */
    @GetMapping(value = "/restablecer/verificar/{token}")
    public ResponseEntity<Integer> verificarToken(@PathVariable("token") String token) {
        int rpta = 0;
        try {
            if (token != null && !token.isEmpty()) {
                TblResetToken rt = tokenService.findByToken(token);
                if (rt != null && rt.getId() > 0) {
                    if (!rt.estaExpirado()) {
                        rpta = 1;
                    }
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }

    /**
     * Toma un token y una contraseña, y luego cambia la contraseña del usuario asociado con el token.
     * 
     * @param token El token que se envió al correo electrónico del usuario.
     * @param clave la nueva contraseña
     * @return Un objeto ResponseEntity.
     */
    @PostMapping(value = "/restablecer/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> restablecerClave(@PathVariable("token") String token, @RequestBody String clave) {
        try {
            TblResetToken rt = tokenService.findByToken(token);
            service.cambiarClave(clave, rt.getUser().getUsername());
            tokenService.eliminar(rt);
        } catch (Exception e) {
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
