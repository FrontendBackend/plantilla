package com.plantillabackend.services;

import java.util.List;

import com.plantillabackend.dtos.AuditoriaDTO;
import com.plantillabackend.dtos.TblUsuarioDTO;
import com.plantillabackend.models.entity.TblUsuario;

public interface UsuarioService {

    /**
     * > Encuentra un usuario por nombre de usuario
     * 
     * @param username El nombre de usuario del usuario que desea encontrar.
     * @return Un objeto TblUsuario.
     */
    public TblUsuario findByUsername(String username);

    /**
     * Devuelve una lista de usuarios.
     * 
     * @return Una lista de objetos TblUsuarioDTO.
     */
    List<TblUsuarioDTO> listarUsuarios();

    /**
     * Devuelve un usuario por id.
     * 
     * @param idUsuario La identificación del usuario.
     * @return Un objeto TblUsuarioDTO.
     */
    TblUsuarioDTO obtenerUsuarioPorId(Long idUsuario);

    /**
     * Crea un usuario.
     * 
     * @param tblUsuarioDTO El objeto que se creará.
     * @return TblUsuarioDTO
     */
    TblUsuarioDTO crearUsuario(TblUsuarioDTO tblUsuarioDTO, AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Modifica el usuario.
     * 
     * @param tblUsuarioDTO Este es el objeto que se enviará a la interfaz.
     * @param tblUsuario    La entidad que será modificada.
     * @return TblUsuarioDTO
     */
    TblUsuarioDTO modificarUsuario(TblUsuarioDTO tblUsuarioDTO, TblUsuario tblUsuario, AuditoriaDTO auditoriaDTO) throws Exception;

    /**
     * Devuelve un objeto TblUsuario que tiene el mismo idUsuario que el pasado como parámetro
     * 
     * @param idUsuario El id del usuario que desea encontrar.
     * @return Un objeto TblUsuario.
     */
    TblUsuario findById(Long idUsuario);
}
