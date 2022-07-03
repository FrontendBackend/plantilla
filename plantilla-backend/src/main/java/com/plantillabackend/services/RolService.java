package com.plantillabackend.services;

import java.util.List;

import com.plantillabackend.dtos.TblRolDTO;
import com.plantillabackend.dtos.TblUsuarioDTO;

public interface RolService {
    
    /**
     * Devuelve una lista de roles.
     * 
     * @return Lista<TblRolDTO>
     */
    List<TblRolDTO> listarRol();

    public void crearRol(TblUsuarioDTO tblUsuarioDTO) throws Exception;
}
