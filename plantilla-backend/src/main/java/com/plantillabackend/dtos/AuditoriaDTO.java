package com.plantillabackend.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuditoriaDTO {
    
    private Long idUsuario;

    private String username;

    private String terminal;

    private String terminalSeg;

    private String noTerminal;

    private Date fecha;

    private String moduloSmptActual;
}
