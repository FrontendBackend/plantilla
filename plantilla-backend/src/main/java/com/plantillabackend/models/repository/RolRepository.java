package com.plantillabackend.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantillabackend.dtos.TblRolDTO;
import com.plantillabackend.models.entity.TblRol;

public interface RolRepository extends JpaRepository<TblRol, Long> {

        /**
         * Quiero devolver una lista de objetos TblRolDTO, pero quiero llenar los
         * objetos TblRolDTO con los
         * resultados de una consulta que devuelve una lista de objetos
         * TblRolDTOResultado
         * 
         * @return Una lista de objetos TblRolDTO.
         */
        @Query("SELECT new com.plantillabackend.dtos.TblRolDTOResultado( "
                        + "r.idRol, r.nuOrden, r.descripcion, r.nombre "
                        + ") "
                        + "FROM TblRol r "
                        + "WHERE r.esRegistro = 1 "
                        + "ORDER BY r.nuOrden "
                        )
        List<TblRolDTO> listarRoles();

        /**
         * Me permite obtener el rol por el ID
         * 
         * @param idRol
         * @return
         */
        @Query("SELECT new com.plantillabackend.dtos.TblRolDTOResultado("
                        + "r.idRol, r.nuOrden, r.descripcion, r.nombre "
                        + ") "
                        + "FROM TblRol r "
                        + "WHERE r.idRol = :idRol "
                        )
        TblRolDTO obtenerRoloPorId(@Param("idRol") Long idRol);

        /**
         * Me permite validar la existencia de numeros de orden de los roles ya registrados
         * 
         * @param idUsuario
         * @param nuOrden
         * @return
         */
        @Query("SELECT new com.plantillabackend.dtos.TblRolDTOResultado("
                        + "r.idRol, "
                        + "TRIM(UPPER(r.nuOrden)) "
                        + " ) "
                        + "FROM TblRol r "
                        + "WHERE r.esRegistro = '1' "
                        + "AND (:idRol IS NULL OR r.idRol != :idRol) "
                        + "AND TRIM(UPPER(r.nuOrden)) = :nuOrden "
                        + " ")
        List<TblRolDTO> existeNumeroOrdenRol(@Param("idRol") Long idRol, @Param("nuOrden") Long nuOrden);

        /**
         * Me permite validar la existencia de los nombres de roles ya registrados
         * 
         * @param idUsuario
         * @param nombre
         * @return
         */
        @Query("SELECT new com.plantillabackend.dtos.TblRolDTOResultado("
                        + "r.idRol, "
                        + "TRIM(UPPER(r.nombre )) "
                        + " ) "
                        + "FROM TblRol r "
                        + "WHERE r.esRegistro = '1' "
                        + "AND (:idRol IS NULL OR r.idRol != :idRol) "
                        + "AND TRIM(UPPER(r.nombre)) = :nombre "
                        + " ")
        List<TblRolDTO> existeNombreRol(@Param("idRol") Long idRol, @Param("nombre") String nombre);
}
