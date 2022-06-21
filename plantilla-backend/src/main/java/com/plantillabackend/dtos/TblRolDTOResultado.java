package com.plantillabackend.dtos;

public class TblRolDTOResultado extends TblRolDTO {
    
    /**
     * @see com.plantillabackend.models.repository.RolRepository#listarRol()
     */
    public TblRolDTOResultado (Long idRol, String descripcion, String nombre) {
        super();
        this.setIdRol(idRol);
        this.setDescripcion(descripcion);
        this.setNombre(nombre);
    }
}
