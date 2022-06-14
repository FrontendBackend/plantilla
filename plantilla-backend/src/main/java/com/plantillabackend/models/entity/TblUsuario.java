package com.plantillabackend.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_USUARIO")
@Data
@NoArgsConstructor
public class TblUsuario implements Serializable {

    // Este es un generador de secuencias.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_USUARIO_SEQ")
    @SequenceGenerator(name = "TBL_USUARIO_SEQ", sequenceName = "TBL_USUARIO_SEQ", allocationSize = 1)
    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;

    // Crear una columna en la base de datos llamada NOMBRE que no sea nula y única.
    @Column(name = "NOMBRE", nullable = false, unique = true)
    private String username;
    
    // Crear una columna en la base de datos llamada CORREO que no permita valores nulos y sea única.
    @Column(name = "CORREO", nullable = false, unique = true)
    private String correo;

    // Una columna en la base de datos que no admite valores NULL.
    @Column(name = "CLAVE", nullable = false)
    private String password;

    // Un valor booleano que se utiliza para habilitar o deshabilitar un usuario.
    @Column(name = "ESTADO", nullable = false)
    private boolean enabled;

    // Una columna en la base de datos que no permite valores NULL.
    @Column(name = "ES_REGISTRO", nullable = false, length = 1)
    private String esRegistro;

    // Esta es una columna en la base de datos que no permite valores NULL.
    @Column(name = "US_CREACION", nullable = true, length = 10)
    private String usCreacion;

    // Esta es una columna en la base de datos que no permite valores NULL.
    @Column(name = "IP_CREACION", nullable = true, length = 9)
    private String ipCreacion;

    // Una columna de fecha en la base de datos que no permite valores NULL.
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_CREACION", nullable = true)
    private Date feCreacion;

    // Crear una columna en la base de datos llamada US_ACTUALIZACION que permita valores nulos y tenga una longitud de 10.
    @Column(name = "US_ACTUALIZACION", nullable = true, length = 10)
    private String usActualizacion;

    // Creando una columna en la base de datos llamada IP_ACTUALIZACION que permita valores nulos y tenga una longitud de 9.
    @Column(name = "IP_ACTUALIZACION", nullable = true, length = 9)
    private String ipActualizacion;

    // Una columna de fecha en la base de datos que no permite valores nulos.
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_ACTUALIZACION", nullable = true)
    private Date feActualizacion;

    // Esta es una relación de muchos a muchos entre el usuario y el rol.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TBL_USUARIO_TBL_ROL", joinColumns = @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO"), inverseJoinColumns = @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL"))
    private List<TblRol> roles;
}
