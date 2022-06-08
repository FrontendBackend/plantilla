package com.plantillabackend.models.entity;

import java.io.Serializable;

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

    // Una columna en la base de datos que no admite valores NULL.
    @Column(name = "CLAVE", nullable = false)
    private String password;

    // Un valor booleano que se utiliza para habilitar o deshabilitar un usuario.
    @Column(name = "ESTADO", nullable = false)
    private boolean enabled;

    // Esta es una relación de muchos a muchos entre el usuario y el rol.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TBL_USUARIO_TBL_ROL", joinColumns = @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO"), inverseJoinColumns = @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL"))
    private List<TblRol> roles;
}
