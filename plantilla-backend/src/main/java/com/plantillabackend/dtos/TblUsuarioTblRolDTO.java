package com.plantillabackend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TblUsuarioTblRolDTO {
    
    // Creando una columna en la base de datos llamada ID_USUARIO_ID_ROL.
    private Long idUsuarioIdRol;

    // Creando una clave foránea en la tabla TBL_USUARIO_TBL_ROL.
    private Long idUsuario;

    // Creando una clave foránea en la tabla TBL_USUARIO_TBL_ROL.
    private Long idRol;
}
