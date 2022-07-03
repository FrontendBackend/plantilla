package com.plantillabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plantillabackend.dtos.TblUsuarioTblRolDTO;
import com.plantillabackend.models.entity.TblUsuarioTblRol;

public interface UsuarioRolRepository extends JpaRepository<TblUsuarioTblRol, Long> {

    @Query("SELECT new com.plantillabackend.dtos.TblUsuarioTblRolDTOResultado("
            + "ur.idUsuarioIdRol, ur.tblUsuario.idUsuario, ur.tblRol.idRol "
            + " ) "
            + "FROM TblUsuarioTblRol ur "
            + "WHERE ur.idUsuarioIdRol = :idUsuarioIdRol "
            )
    TblUsuarioTblRolDTO obtenerUsuarioRolPorId(@Param("idUsuarioIdRol") Long idUsuarioIdRol);
}
