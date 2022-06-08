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
@Table(name = "TBL_MENU_TBL_ROL")
@Data
@NoArgsConstructor
public class TblMenuTblRol implements Serializable {
    
    // Crear una secuencia en la base de datos.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_MENU_TBL_ROL_SEQ")
    @SequenceGenerator(name = "TBL_MENU_TBL_ROL_SEQ", sequenceName = "TBL_MENU_TBL_ROL_SEQ", allocationSize = 1)
    @Column(name = "ID_MENU_ID_ROL", nullable = false)
    private Long idMenuIdRol;

    // Una relación ManyToOne entre TblMenuTblRol y TblMenu.
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MENU", nullable = false)
    private TblMenu tblMenu;

    // Una relación ManyToOne entre TblMenuTblRol y TblRol.
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ROL", nullable = false)
    private TblRol tblRol;
}
