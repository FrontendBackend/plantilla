package com.plantillabackend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantillabackend.dtos.TblUsuarioDTO;
import com.plantillabackend.dtos.TblUsuarioTblRolDTO;
import com.plantillabackend.models.entity.TblRol;
import com.plantillabackend.models.entity.TblUsuario;
import com.plantillabackend.models.entity.TblUsuarioTblRol;
import com.plantillabackend.models.repository.UsuarioRolRepository;
import com.plantillabackend.services.UsuarioRolService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UsuarioRolServiceImpl implements UsuarioRolService{

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;
    
    @Override
    @Transactional(readOnly = false)
    public TblUsuarioTblRolDTO crearUsuarioRol(TblUsuario tblUsuario) throws Exception {

        TblUsuarioTblRol tblUsuarioTblRol = new TblUsuarioTblRol();

        tblUsuarioTblRol.setTblUsuario(new TblUsuario());
        tblUsuarioTblRol.getTblUsuario().setIdUsuario(tblUsuario.getIdUsuario());
        
        tblUsuarioTblRol.setTblRol(new TblRol());
        tblUsuarioTblRol.getTblRol().setIdRol(1L);
        // tblUsuarioTblRol.getTblRol().setIdRol(tblUsuarioDTO.getIdUsuario());

        TblUsuarioTblRol tblUsuarioTblRolCreado = this.usuarioRolRepository.save(tblUsuarioTblRol);

        TblUsuarioTblRolDTO tblUsuarioTblRolDTOCreado = this.obtenerUsuarioRolPorId(tblUsuarioTblRolCreado.getIdUsuarioIdRol());

        return tblUsuarioTblRolDTOCreado;
    }

    @Override
    public TblUsuarioTblRolDTO obtenerUsuarioRolPorId(Long idUsuarioIdRol) {
        return this.usuarioRolRepository.obtenerUsuarioRolPorId(idUsuarioIdRol);
    }
    
}
