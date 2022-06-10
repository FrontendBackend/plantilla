package com.plantillabackend.models.repository;


import com.plantillabackend.models.entity.TblResetToken;

public interface ResetTokenRepository extends GenericRepository<TblResetToken, Long>{
    
    /**
     * > Encuentra un token de reinicio por su token
     * 
     * @param token El token a buscar.
     * @return Un objeto TblResetToken.
     */
    TblResetToken findByToken(String token);
}
