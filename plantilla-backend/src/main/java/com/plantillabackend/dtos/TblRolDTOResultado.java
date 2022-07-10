package com.plantillabackend.dtos;

public class TblRolDTOResultado extends TblRolDTO {
    
    /**
     * @see com.plantillabackend.models.repository.RolRepository#listarRoles()
     */
    public TblRolDTOResultado (Long idRol, Long nuOrden, String descripcion, String nombre) {
        super();
        this.setIdRol(idRol);
        this.setNuOrden(nuOrden);
        this.setDescripcion(descripcion);
        this.setNombre(nombre);
    }
    
    /**
     * @see com.plantillabackend.models.repository.RolRepository#existeNumeroOrdenRol
     */
    public TblRolDTOResultado (Long idRol, Long nuOrden) {
        super();
        this.setIdRol(idRol);
        this.setNuOrden(nuOrden);
    }
    
    /**
     * @see com.plantillabackend.models.repository.RolRepository#existeNombreRol
     */
    public TblRolDTOResultado (Long idRol, String nombre) {
        super();
        this.setIdRol(idRol);
        this.setNombre(nombre);
    }
}
