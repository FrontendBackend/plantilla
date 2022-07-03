package com.plantillabackend.dtos;


public class TblUsuarioDTOResultado extends TblUsuarioDTO {
    
    /**
     * @see com.plantillabackend.models.repository.UsuarioRepository#listarUsuarios
     * @see com.plantillabackend.models.repository.UsuarioRepository#obtenerUsuarioPorId
     */
    public TblUsuarioDTOResultado (Long idUsuario, String username, String correo, String password, Boolean enabled) {
        super();
        this.setIdUsuario(idUsuario);
        this.setUsername(username);
        this.setCorreo(correo);
        this.setPassword(password);
        this.setEnabled(enabled);
    }
    
    /**
     * @see com.plantillabackend.models.repository.UsuarioRepository#existeUsuario
     */
    public TblUsuarioDTOResultado (Long idUsuario, String username) {
        super();
        this.setIdUsuario(idUsuario);
        this.setUsername(username);
    }

    /**
     * @see com.plantillabackend.models.repository.UsuarioRepository#existeCorreo
     */
    public TblUsuarioDTOResultado (Long idUsuario, String correo, String indefinido) {
        super();
        this.setIdUsuario(idUsuario);
        this.setCorreo(correo);
    }
}
