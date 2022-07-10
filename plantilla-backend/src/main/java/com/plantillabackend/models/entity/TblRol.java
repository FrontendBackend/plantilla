package com.plantillabackend.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_ROL")
@Data
@NoArgsConstructor
public class TblRol implements Serializable {
    
    // Crear una secuencia en la base de datos.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_ROL_SEQ")
    @SequenceGenerator(name = "TBL_ROL_SEQ", sequenceName = "TBL_ROL_SEQ", allocationSize = 1)
    @Column(name = "ID_ROL", nullable = false)
    private Long idRol;

    // Creando una columna en la base de datos llamada NOMBRE.
    @Column(name = "NOMBRE")
    private String nombre;

    // Creando una columna en la base de datos llamada DESCRIPCION.
    @Column(name = "DESCRIPCION")
    private String descripcion;

    // Campo NUMERO DE ORDEN y no es mandatorio.
    @Column(name = "NU_ORDEN")
    private Long nuOrden;

    // Una columna en la base de datos que no permite valores NULL.
    @Column(name = "ES_REGISTRO", nullable = false, length = 1)
    private String esRegistro;

    // Esta es una columna en la base de datos que no permite valores NULL.
    @Column(name = "US_CREACION", nullable = true, length = 50)
    private String usCreacion;

    // Esta es una columna en la base de datos que no permite valores NULL.
    @Column(name = "IP_CREACION", nullable = true)
    private String ipCreacion;

    // Una columna de fecha en la base de datos que no permite valores NULL.
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_CREACION", nullable = true)
    private Date feCreacion;

    // Crear una columna en la base de datos llamada US_ACTUALIZACION que permita valores nulos y tenga una longitud de 10.
    @Column(name = "US_ACTUALIZACION", nullable = true, length = 50)
    private String usActualizacion;

    // Creando una columna en la base de datos llamada IP_ACTUALIZACION que permita valores nulos y tenga una longitud de 9.
    @Column(name = "IP_ACTUALIZACION", nullable = true)
    private String ipActualizacion;

    // Una columna de fecha en la base de datos que no permite valores nulos.
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_ACTUALIZACION", nullable = true)
    private Date feActualizacion;
}
