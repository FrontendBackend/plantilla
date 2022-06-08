package com.plantillabackend.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TblResetTokenDTO {
    
    // Crear una secuencia en la base de datos.
    private Long id;

    // Crear una columna en la base de datos llamada token que sea única y no pueda
    // ser nula.
    private String token;

    // Crear una relación entre las tablas TblResetToken y TblUsuario.
    private Long idUsuario;

    // Creando una columna en la base de datos llamada caducidad que no puede ser
    // nula.
    private LocalDateTime expiracion;
}
