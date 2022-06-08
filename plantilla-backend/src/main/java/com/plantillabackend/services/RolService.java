package com.plantillabackend.services;

import java.util.List;

import com.plantillabackend.dtos.TblRolDTO;

public interface RolService {
    
    /**
     * Devuelve una lista de roles.
     * 
     * @return Lista<TblRolDTO>
     */
    List<TblRolDTO> listarRol();
}
