package com.plantillabackend.models.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.plantillabackend.models.entity.TblUsuario;

public interface LoginRepository extends GenericRepository<TblUsuario, Integer> {
    
    /**
     * Esta función devuelve un objeto TblUsuario, que es un objeto de usuario, y toma como parámetro
     * un String, que es el nombre de usuario del usuario.
     * 
     * @param usuario El nombre del parámetro que se pasará al método.
     * @return Una lista de objetos TblUsuario.
     */
    @Query("FROM TblUsuario us where us.correo =:usuario")
    TblUsuario verificarNombreUsuario(@Param("usuario") String usuario);

    /**
     * Esta función actualizará la contraseña del usuario con el nombre de usuario 'nombre' al valor
     * 'clave'
     * 
     * @param clave la nueva contraseña
     * @param nombre es el nombre de usuario del usuario
     */
    @Transactional
    @Modifying
    @Query("UPDATE TblUsuario us SET us.password =:clave WHERE us.correo =:nombre")
    void cambiarClave(@Param("clave") String clave, @Param("nombre") String nombre);
}
