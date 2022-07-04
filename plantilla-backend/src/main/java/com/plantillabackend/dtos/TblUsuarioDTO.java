package com.plantillabackend.dtos;

import java.util.Date;
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

    // Crear una columna en la base de datos llamada CORREO que no permita valores nulos y sea única.
    private String correo;

    // Una columna en la base de datos que no admite valores NULL.
    private String password;

    // Un valor booleano que se utiliza para habilitar o deshabilitar un usuario.
    private boolean enabled;

    // Una columna en la base de datos que no permite valores NULL.
    private String esRegistro;

    // Esta es una columna en la base de datos que no permite valores NULL.
    private String usCreacion;

    // Esta es una columna en la base de datos que no permite valores NULL.
    private String ipCreacion;

    // Una columna de fecha en la base de datos que no permite valores NULL.
    private Date feCreacion;

    // Crear una columna en la base de datos llamada US_ACTUALIZACION que permita valores nulos y tenga una longitud de 10.
    private String usActualizacion;

    // Creando una columna en la base de datos llamada IP_ACTUALIZACION que permita valores nulos y tenga una longitud de 9.
    private String ipActualizacion;

    // Una columna de fecha en la base de datos que no permite valores nulos.
    private Date feActualizacion;

    // Esta es una relación de muchos a muchos entre el usuario y el rol.
    private List<TblRol> roles;

    /**
     * Variables auxiliares
     */

    // Campo id de la tabla rol
    private Long descIdRol;
}
