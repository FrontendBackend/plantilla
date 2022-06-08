package com.plantillabackend.services;

import com.plantillabackend.models.entity.TblUsuario;

public interface LoginService {
    
    /**
     * Verifica el nombre de usuario.
     * 
     * @param usuario El nombre de usuario a verificar.
     * @return un objeto TblUsuario.
     */
    TblUsuario verificarNombreUsuario(String usuario);

    /**
     * Esta función cambia la contraseña del usuario con el nombre dado.
     * 
     * @param clave La nueva contraseña.
     * @param nombre El nombre del usuario cuya contraseña desea cambiar.
     */
    void cambiarClave(String clave, String nombre);
}
