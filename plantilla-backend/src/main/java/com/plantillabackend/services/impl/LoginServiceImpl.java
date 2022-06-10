package com.plantillabackend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.plantillabackend.models.entity.TblUsuario;
import com.plantillabackend.models.repository.LoginRepository;
import com.plantillabackend.services.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    private LoginRepository repo;

    /**
     * "Esta función devuelve un objeto TblUsuario si el nombre de usuario existe en la base de datos,
     * de lo contrario, devuelve nulo".
     * 
     * // Sql
     * SELECCIONE * DESDE tbl_usuario DONDE usuario = ?
     * 
     * @param usuario Cuerda
     * @return Un objeto TblUsuario.
     */
    @Override
    public TblUsuario verificarNombreUsuario(String usuario) {
        return repo.verificarNombreUsuario(usuario);
    }

    /**
     * Toma una cadena, la codifica y luego la guarda en la base de datos.
     * 
     * @param clave la contraseña
     * @param nombre El nombre del usuario
     */
    @Override
    public void cambiarClave(String clave, String nombre) {
        repo.cambiarClave(bcrypt.encode(clave), nombre);
    }
}
