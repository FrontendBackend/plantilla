package com.plantillabackend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_USUARIO_TBL_ROL")
@Data
@NoArgsConstructor
public class TblUsuarioTblRol implements Serializable {

    // Una clave principal.
    @Id
    // Crear una secuencia en la base de datos.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_USUARIO_TBL_ROL_SEQ")
    @SequenceGenerator(name = "TBL_USUARIO_TBL_ROL_SEQ", sequenceName = "TBL_USUARIO_TBL_ROL_SEQ", allocationSize = 1)
    // Creando una columna en la base de datos llamada ID_USUARIO_ID_ROL.
    @Column(name = "ID_USUARIO_ID_ROL", nullable = false)
    private Long idUsuarioIdRol;

    // Creando una clave foránea en la tabla TBL_USUARIO_TBL_ROL.
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private TblUsuario tblUsuario;

    // Creando una clave foránea en la tabla TBL_USUARIO_TBL_ROL.
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ROL", nullable = false)
    private TblRol tblRol;
}
