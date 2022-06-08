package com.plantillabackend.dtos;

import java.util.List;

import com.plantillabackend.models.entity.TblRol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TblUsuarioDTO {
    
    // Este es un generador de secuencias.
    private Long idUsuario;

    // Crear una columna en la base de datos llamada NOMBRE que no sea nula y única.
    private String username;

    // Una columna en la base de datos que no admite valores NULL.
    private String password;

    // Un valor booleano que se utiliza para habilitar o deshabilitar un usuario.
    private boolean enabled;

    // Esta es una relación de muchos a muchos entre el usuario y el rol.
    private List<TblRol> roles;
}
