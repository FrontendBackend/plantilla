package com.plantillabackend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TblMenuTblRolDTO {
    
    // Crear una secuencia en la base de datos.
    private Long idMenuIdRol;

    // Una relación ManyToOne entre TblMenuTblRol y TblMenu.
    private Long idMenu;

    // Una relación ManyToOne entre TblMenuTblRol y TblRol.
    private Long idRol;
}
