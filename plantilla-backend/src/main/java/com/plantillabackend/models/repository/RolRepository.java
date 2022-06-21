package com.plantillabackend.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.plantillabackend.dtos.TblRolDTO;
import com.plantillabackend.models.entity.TblRol;

public interface RolRepository extends JpaRepository<TblRol, Long> {

    
   /**
    * Quiero devolver una lista de objetos TblRolDTO, pero quiero llenar los objetos TblRolDTO con los
    * resultados de una consulta que devuelve una lista de objetos TblRolDTOResultado
    * 
    * @return Una lista de objetos TblRolDTO.
    */
    @Query("SELECT new com.plantillabackend.dtos.TblRolDTOResultado( "
            + "r.idRol, r.descripcion, r.nombre "
            + ") "
            + "FROM TblRol r "
    )
    List<TblRolDTO> listarRoles();
}
