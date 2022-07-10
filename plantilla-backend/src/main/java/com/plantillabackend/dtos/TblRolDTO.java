package com.plantillabackend.dtos;

import java.util.Date;

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

    // Campo NUMERO DE ORDEN y no es mandatorio.
    private Long nuOrden;

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
}
