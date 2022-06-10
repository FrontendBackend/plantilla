package com.plantillabackend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plantillabackend.models.entity.TblResetToken;
import com.plantillabackend.models.repository.ResetTokenRepository;
import com.plantillabackend.services.ResetTokenService;

@Service
public class ResetTokenServiceImpl implements ResetTokenService {

    @Autowired
    private ResetTokenRepository repo;

    /**
     * Encuentra una ficha por su ficha
     * 
     * @param token el token que genera el sistema
     * @return Un objeto TblResetToken
     */
    @Override
    public TblResetToken findByToken(String token) {
        return repo.findByToken(token);
    }

    /**
     * Guarda el token en la base de datos.
     * 
     * @param token El token a guardar
     */
    @Override
    public void guardar(TblResetToken token) {
        repo.save(token);
    }

    /**
     * La función elimina el token de la base de datos.
     * 
     * @param token el token que se eliminará
     */
    @Override
    public void eliminar(TblResetToken token) {
        repo.delete(token);
    }
}
