package com.plantillabackend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TblRolDTO {
    
    // Crear una secuencia en la base de datos.
    private Long idRol;

    // Creando una columna en la base de datos llamada NOMBRE.
    private String nombre;

    // Creando una columna en la base de datos llamada DESCRIPCION.
    private String descripcion;
}
