package com.plantillabackend.dtos;

import java.util.List;

import com.plantillabackend.models.entity.TblRol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TblMenuDTO {

    // Crear una secuencia en la base de datos.
    private Long idMenu;

    // Creación de una columna en la base de datos.
    private String icono;

    // Creación de una columna en la base de datos.
    private String nombre;

    // Creación de una columna en la base de datos.
    private String url;

    // Creación de una columna en la base de datos.
    private Long orden;

    // Creación de una tabla de unión entre TBL_MENU y TBL_ROL.
    private List<TblRol> roles;
}
