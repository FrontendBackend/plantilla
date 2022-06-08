package com.plantillabackend.services;

import com.plantillabackend.models.entity.TblUsuario;

public interface UsuarioService {
    
    /**
     * > Encuentra un usuario por nombre de usuario
     * 
     * @param username El nombre de usuario del usuario que desea encontrar.
     * @return Un objeto TblUsuario.
     */
    public TblUsuario findByUsername(String username);
}
