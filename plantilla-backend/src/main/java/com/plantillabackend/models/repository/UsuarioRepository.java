package com.plantillabackend.models.repository;

import com.plantillabackend.models.entity.TblUsuario;

public interface UsuarioRepository extends GenericRepository<TblUsuario, Long> {
    
    /**
     * Encuentra un usuario por nombre de usuario.
     * 
     * @param username El nombre de usuario del usuario a buscar.
     * @return Un único objeto de tipo TblUsuario
     */
    TblUsuario findOneByUsername(String username);
}
