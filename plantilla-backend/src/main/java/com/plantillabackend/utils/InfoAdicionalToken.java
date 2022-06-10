package com.plantillabackend.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plantillabackend.models.entity.TblUsuario;
import com.plantillabackend.services.UsuarioService;

@Deprecated
@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Toma el token de acceso y el objeto de autenticación y devuelve el token de acceso con
     * información adicional
     * 
     * @param accessToken El token de acceso que se está mejorando.
     * @param authentication El objeto de autenticación que se usó para generar el token.
     * @return El token está siendo devuelto.
     */
    @Override
    @Transactional(readOnly = true)
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        TblUsuario usuario = usuarioService.findByUsername(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("info_adicional", "Hola que tal!: ".concat(authentication.getName()));

        info.put("nombre", usuario.getUsername());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}
