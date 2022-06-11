package com.plantillabackend.dtos;


public class TblUsuarioDTOResultado extends TblUsuarioDTO {
    
    // un constructor
    public TblUsuarioDTOResultado (Long idUsuario, String username, String password, Boolean enabled) {
        super();
        this.setIdUsuario(idUsuario);
        this.setUsername(username);
        this.setPassword(password);
        this.setEnabled(enabled);
    }
}
