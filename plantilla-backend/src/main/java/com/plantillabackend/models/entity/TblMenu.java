package com.plantillabackend.models.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_MENU")
@Data
@NoArgsConstructor
public class TblMenu implements Serializable {

    // Crear una secuencia en la base de datos.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_MENU_SEQ")
    @SequenceGenerator(name = "TBL_MENU_SEQ", sequenceName = "TBL_MENU_SEQ", allocationSize = 1)
    @Column(name = "ID_MENU", nullable = false)
    private Long idMenu;

    // Creación de una columna en la base de datos.
    @Column(name = "ICONO", length = 20)
    private String icono;

    // Creación de una columna en la base de datos.
    @Column(name = "NOMBRE", length = 20)
    private String nombre;

    // Creación de una columna en la base de datos.
    @Column(name = "URL", length = 50)
    private String url;

    // Creación de una columna en la base de datos.
    @Column(name = "ORDEN", length = 5)
    private Long orden;

    // Creación de una tabla de unión entre TBL_MENU y TBL_ROL.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TBL_MENU_TBL_ROL", joinColumns = @JoinColumn(name = "ID_MENU", referencedColumnName = "ID_MENU"), inverseJoinColumns = @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL"))
    private List<TblRol> roles;
}
