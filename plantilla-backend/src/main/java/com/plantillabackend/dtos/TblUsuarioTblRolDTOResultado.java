package com.plantillabackend.dtos;

public class TblUsuarioTblRolDTOResultado extends TblUsuarioTblRolDTO {
    
    /**
     * @see com.plantillabackend.models.repository.UsuarioRolRepository#obtenerUsuarioRolPorId
     */
    public TblUsuarioTblRolDTOResultado (Long idUsuarioIdRol, Long idUsuario, Long idRol) {
        super();
        this.setIdUsuarioIdRol(idUsuarioIdRol);
        this.setIdUsuario(idUsuario);
        this.setIdRol(idRol);
    }
}
