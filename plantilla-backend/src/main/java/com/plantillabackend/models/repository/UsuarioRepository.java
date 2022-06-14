package com.plantillabackend.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantillabackend.dtos.TblUsuarioDTO;
import com.plantillabackend.models.entity.TblUsuario;

public interface UsuarioRepository extends GenericRepository<TblUsuario, Long> {
    
    /**
     * Encuentra un usuario por nombre de usuario.
     * 
     * @param username El nombre de usuario del usuario a buscar.
     * @return Un único objeto de tipo TblUsuario
     */
    TblUsuario findOneByUsername(String username);

    /**
     * Devuelve una lista de objetos TblUsuarioDTO, que se crean a partir de la tabla TblUsuarioDTO, y
     * se llama al constructor TblUsuarioDTO con los siguientes parámetros: idUsuario, usuario,
     * contraseña, habilitado
     * 
     * @return Una lista de objetos TblUsuarioDTO.
     */
    @Query("SELECT new com.plantillabackend.dtos.TblUsuarioDTOResultado("
            + "usu.idUsuario, usu.username, usu.correo, usu.password, usu.enabled "
            + " ) "
            + "FROM TblUsuario usu "
            // + "WHERE usu.esRegistro = '1' "
            )
    List<TblUsuarioDTO> listarUsuarios();

   /**
    * Devuelve un objeto TblUsuarioDTO, que es un DTO (Data Transfer Object) que contiene los campos
    * idUsuario, usuario, contraseña y habilitado.
    * 
    * Los campos se obtienen de la tabla TblUsuario, que es la tabla que contiene la información del
    * usuario.
    * 
    * La función se llama obtenerUsuarioPorId, y toma un parámetro Long llamado idUsuario.
    * 
    * El parámetro idUsuario se utiliza para filtrar los resultados.
    * 
    * La función devuelve un solo resultado, y es el resultado que coincide con el parámetro idUsuario.
    * 
    * La función se llama desde la clase UsuarioServiceImpl, que es la clase de servicio que contiene
    * la lógica de negocio.
    * 
    * La clase UsuarioServiceImpl se llama desde la clase UsuarioController, que es la clase de
    * controlador que contiene los extremos REST.
    * 
    * la u
    * 
    * @param idUsuario El id del usuario que desea obtener.
    * @return Un objeto DTO
    */
    @Query("SELECT new com.plantillabackend.dtos.TblUsuarioDTOResultado("
            + "usu.idUsuario, usu.username, usu.correo, usu.password, usu.enabled "
            + " ) "
            + "FROM TblUsuario usu "
            + "WHERE usu.idUsuario = :idUsuario "
            )
    TblUsuarioDTO obtenerUsuarioPorId(@Param("idUsuario") Long idUsuario);
}
