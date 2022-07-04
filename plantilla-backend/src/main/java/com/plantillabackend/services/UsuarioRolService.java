package com.plantillabackend.services;

import com.plantillabackend.dtos.TblUsuarioDTO;
import com.plantillabackend.dtos.TblUsuarioTblRolDTO;
import com.plantillabackend.models.entity.TblUsuario;

public interface UsuarioRolService {

    public TblUsuarioTblRolDTO crearUsuarioRol(TblUsuario tblUsuario, TblUsuarioDTO tblUsuarioDTO) throws Exception;

    TblUsuarioTblRolDTO obtenerUsuarioRolPorId(Long idUsuarioIdRol);
}
