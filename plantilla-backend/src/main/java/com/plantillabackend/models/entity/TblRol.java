package com.plantillabackend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
}
