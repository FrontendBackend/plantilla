package com.plantillabackend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantillabackend.dtos.AuditoriaDTO;
import com.plantillabackend.dtos.TblUsuarioDTO;
import com.plantillabackend.models.entity.TblUsuario;
import com.plantillabackend.models.repository.UsuarioRepository;
import com.plantillabackend.services.UsuarioService;
import com.plantillabackend.utils.ConstantesUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    // public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private BCryptPasswordEncoder bcrypt;
    
    /**
     * Toma un nombre de usuario, encuentra al usuario en la base de datos y
     * devuelve un objeto
     * UserDetails con el nombre de usuario, la contraseña y las funciones del
     * usuario.
     * 
     * @param username El nombre de usuario del usuario.
     * @return Un objeto de usuario
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TblUsuario usuario = repo.findOneByUsername(username);

        if (usuario == null) {
            log.error("Error en el login: no existe el usuario '" + username + "' en el sistema!");
            throw new UsernameNotFoundException(
                    "Error en el login: no existe el usuario '" + username + "' en el sistema!");
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> log.info("Role: " + authority.getAuthority()))
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

    /**
     * La función listarUsuarios() es una función que devuelve una lista de objetos
     * TblUsuarioDTO
     * 
     * @return Una lista de objetos TblUsuarioDTO.
     */
    @Override
    public List<TblUsuarioDTO> listarUsuarios() {
        return this.repo.listarUsuarios();
    }

    /**
     * La función obtenerUsuarioPorId es una función que devuelve un objeto
     * TblUsuarioDTO
     * 
     * @param idUsuario Largo
     * @return Un objeto TblUsuarioDTO.
     */
    @Override
    public TblUsuarioDTO obtenerUsuarioPorId(Long idUsuario) {
        return this.repo.obtenerUsuarioPorId(idUsuario);
    }

   
    /**
     * Crea un nuevo usuario en la base de datos.
     * 
     * @param tblUsuarioDTO es el objeto que estoy enviando al servicio
     * @param auditoriaDTO Es un DTO que contiene el nombre de usuario, terminal y fecha.
     * @return Un objeto TblUsuarioDTO
     */
    @Override
    @Transactional(readOnly = false)
    public TblUsuarioDTO crearUsuario(TblUsuarioDTO tblUsuarioDTO, AuditoriaDTO auditoriaDTO) throws Exception {
        TblUsuario tblUsuario = new TblUsuario();

        this.configurarValores(tblUsuarioDTO, tblUsuario);

        tblUsuario.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        tblUsuario.setFeCreacion(auditoriaDTO.getFecha());
        tblUsuario.setUsCreacion(auditoriaDTO.getUsername());
        tblUsuario.setIpCreacion(auditoriaDTO.getTerminal());

        TblUsuario tblUsuarioCreado = this.repo.save(tblUsuario);

        TblUsuarioDTO tblUsuarioDTOCreado = this.obtenerUsuarioPorId(tblUsuarioCreado.getIdUsuario());

        return tblUsuarioDTOCreado;
    }

    /**
     * Toma un objeto DTO, crea un nuevo objeto de entidad y luego copia los valores del DTO a la
     * entidad.
     * 
     * @param tblUsuarioDTO es el objeto que viene de la interfaz
     * @param tblUsuario es la entidad que voy a persistir
     * @return Un objeto TblUsuario
     */
    @Transactional(readOnly = false)
    private TblUsuario configurarValores(TblUsuarioDTO tblUsuarioDTO, TblUsuario tblUsuario) {

        tblUsuario.setUsername(tblUsuarioDTO.getUsername());
        tblUsuario.setPassword(bcrypt.encode(tblUsuarioDTO.getPassword()));
        tblUsuario.setEnabled(ConstantesUtil.ENABLED);

        return tblUsuario;
    }

    /**
     * Toma un objeto TblUsuarioDTO, un objeto TblUsuario y un objeto AuditoriaDTO, y devuelve un
     * objeto TblUsuarioDTO
     * 
     * @param tblUsuarioDTO es el objeto que viene de la interfaz
     * @param tblUsuario es el objeto que estoy tratando de actualizar
     * @param auditoriaDTO Es un DTO que contiene el nombre de usuario, la fecha y el terminal.
     * @return Un objeto TblUsuarioDTO
     */
    @Transactional(readOnly = false)
    @Override
    public TblUsuarioDTO modificarUsuario(TblUsuarioDTO tblUsuarioDTO, TblUsuario tblUsuario, AuditoriaDTO auditoriaDTO) throws Exception {

        this.configurarValores(tblUsuarioDTO, tblUsuario);

        tblUsuario.setFeActualizacion(auditoriaDTO.getFecha());
        tblUsuario.setUsActualizacion(auditoriaDTO.getUsername());
        tblUsuario.setIpActualizacion(auditoriaDTO.getTerminal());

        TblUsuario tblUsuarioModificado = this.repo.save(tblUsuario);

        TblUsuarioDTO tblUsuarioDTOModificado = this.obtenerUsuarioPorId(tblUsuarioModificado.getIdUsuario());

        return tblUsuarioDTOModificado;
    }

    /**
     * Si el idUsuario se encuentra en la base de datos, devuelve el objeto, de lo contrario, devuelve
     * nulo
     * 
     * @param idUsuario Largo
     * @return El método devuelve un objeto TblUsuario.
     */
    @Override
    public TblUsuario findById(Long idUsuario) {
        return repo.findById(idUsuario).orElse(null);
    }
}
