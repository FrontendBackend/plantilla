package com.plantillabackend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantillabackend.dtos.AuditoriaDTO;
import com.plantillabackend.dtos.TblRolDTO;
import com.plantillabackend.dtos.TblUsuarioDTO;
import com.plantillabackend.models.entity.TblRol;
import com.plantillabackend.models.repository.RolRepository;
import com.plantillabackend.services.RolService;
import com.plantillabackend.utils.ConstantesUtil;

@Service
@Transactional(readOnly = true)
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<TblRolDTO> listarRol() {
        return rolRepository.listarRoles();
    }

    
    @Override
    @Transactional(readOnly = false)
    public TblRolDTO crearRol(TblRolDTO tblRolDTO, AuditoriaDTO auditoriaDTO) throws Exception {

        TblRol tblRol = new TblRol();

        tblRol.setNombre(tblRolDTO.getNombre());
        tblRol.setNuOrden(tblRolDTO.getNuOrden());
        tblRol.setDescripcion(tblRolDTO.getDescripcion());

        tblRol.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        tblRol.setFeCreacion(auditoriaDTO.getFecha());
        tblRol.setUsCreacion(auditoriaDTO.getUsername());
        tblRol.setIpCreacion(auditoriaDTO.getTerminal());

        TblRol tblRolCreado = this.rolRepository.save(tblRol);

        TblRolDTO tblRolDTOCreado = this.obtenerRoloPorId(tblRolCreado.getIdRol());

        return tblRolDTOCreado;
    }

    @Override
    @Transactional(readOnly = false)
    public TblRolDTO modificarRol(TblRolDTO tblRolDTO, TblRol tblRol, AuditoriaDTO auditoriaDTO) throws Exception {

        tblRol.setNombre(tblRolDTO.getNombre());
        tblRol.setNuOrden(tblRolDTO.getNuOrden());
        tblRol.setDescripcion(tblRolDTO.getDescripcion());

        tblRol.setFeActualizacion(auditoriaDTO.getFecha());
        tblRol.setUsActualizacion(auditoriaDTO.getUsername());
        tblRol.setIpActualizacion(auditoriaDTO.getTerminal());

        TblRol tblRolModificado = this.rolRepository.save(tblRol);

        TblRolDTO tblRolDTOModificado = this.obtenerRoloPorId(tblRolModificado.getIdRol());

        return tblRolDTOModificado;
    }

    @Override
    public TblRolDTO obtenerRoloPorId(Long idRol) {
        return this.rolRepository.obtenerRoloPorId(idRol);
    }

    @Override
    public List<TblRolDTO> existeNumeroOrdenRol(Long idRol, Long nuOrden) {
        List<TblRolDTO> lTblUsuarioDTO = this.rolRepository.existeNumeroOrdenRol(idRol, nuOrden);
		return lTblUsuarioDTO;
    }

    @Override
    public List<TblRolDTO> existeNombreRol(Long idRol, String nombre) {
        List<TblRolDTO> lTblUsuarioDTO = this.rolRepository.existeNombreRol(idRol, nombre.toUpperCase());
		return lTblUsuarioDTO;
    }

    @Override
    public TblRol findById(Long idRol) {
        return rolRepository.findById(idRol).orElse(null);
    }
}
