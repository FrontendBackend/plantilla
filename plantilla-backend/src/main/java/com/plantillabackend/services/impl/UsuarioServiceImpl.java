package com.plantillabackend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantillabackend.models.entity.TblUsuario;
import com.plantillabackend.models.repository.UsuarioRepository;
import com.plantillabackend.services.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService{
// public class UsuarioServiceImpl implements UsuarioService{

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository repo;
    
    /**
     * Toma un nombre de usuario, encuentra al usuario en la base de datos y devuelve un objeto
     * UserDetails con el nombre de usuario, la contraseña y las funciones del usuario.
     * 
     * @param username El nombre de usuario del usuario.
     * @return Un objeto de usuario
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TblUsuario usuario = repo.findOneByUsername(username);

        if (usuario == null) {
            logger.error("Error en el login: no existe el usuario '" + username + "' en el sistema!");
            throw new UsernameNotFoundException(
                    "Error en el login: no existe el usuario '" + username + "' en el sistema!");
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true,
                authorities);
    }

    /**
     * Encuentre un usuario por nombre de usuario y devuélvalo.
     * 
     * @param username el nombre de usuario del usuario a buscar
     * @return Un objeto TblUsuario.
     */
    @Override
    @Transactional(readOnly = true)
    public TblUsuario findByUsername(String username) {
        return repo.findOneByUsername(username);
    }
}
