package com.plantillabackend.services;

import com.plantillabackend.models.entity.TblResetToken;

public interface ResetTokenService {
    
    /**
     * > Encuentra un token de reinicio por su token
     * 
     * @param token El token a buscar.
     * @return Un objeto TblResetToken.
     */
    TblResetToken findByToken(String token);

    /**
     * > Guarda un token de reseteo de contraseña en la base de datos
     * 
     * @param token El token que se va a guardar.
     */
    void guardar(TblResetToken token);

    /**
     * Elimina un token.
     * 
     * @param token El token que se va a eliminar.
     */
    void eliminar(TblResetToken token);
}
