package com.plantillabackend.services;

import java.util.List;

import com.plantillabackend.dtos.AuditoriaDTO;
import com.plantillabackend.dtos.TblRolDTO;
import com.plantillabackend.dtos.TblUsuarioDTO;
import com.plantillabackend.models.entity.TblRol;

public interface RolService {

    /**
     * Devuelve una lista de roles.
     * 
     * @return Lista<TblRolDTO>
     */
    List<TblRolDTO> listarRol();

    /**
     * Me permite crear nuevos roles de usuario
     * 
     * @param tblRolDTO
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    public TblRolDTO crearRol(TblRolDTO tblRolDTO, AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Me permite modificar un rol de usuario
     * 
     * @param tblRolDTO
     * @param tblRol
     * @param auditoriaDTO
     * @return
     * @throws Exception
     */
    public TblRolDTO modificarRol(TblRolDTO tblRolDTO, TblRol tblRol, AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Me permite obtener el rol por el ID
     * 
     * @param idRol
     * @return
     */
    TblRolDTO obtenerRoloPorId(Long idRol);

    /**
     * Me permite validar la existencia de numeros de orden de los roles ya
     * registrados
     * 
     * @param idUsuario
     * @param nuOrden
     * @return
     */
    List<TblRolDTO> existeNumeroOrdenRol(Long idRol, Long nuOrden);

    /**
     * Me permite validar la existencia de los nombres de roles ya registrados
     * 
     * @param idUsuario
     * @param nombre
     * @return
     */
    List<TblRolDTO> existeNombreRol(Long idRol, String nombre);

    /**
     * Devuelve un objeto TblRol que tiene el mismo idRol que el pasado como parámetro
     * 
     * @param idRol El id del rol que desea encontrar.
     * @return Un objeto TblRol.
     */
    public TblRol findById(Long idRol);
}
