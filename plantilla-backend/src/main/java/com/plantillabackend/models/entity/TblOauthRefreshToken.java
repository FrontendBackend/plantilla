package com.plantillabackend.models.entity;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity // TODO: Esta tabla queda en deshuso porque no se va guardar los tokens en BD debido a que ahora se va utilizar los tokens en memorias
// @Table(name = "OAUTH_REFRESH_TOKEN")
@Data
@NoArgsConstructor
public class TblOauthRefreshToken implements Serializable {

    // Una clave principal.
    @Id
    @Column(name = "TOKEN_ID", nullable = false, length = 256)
    private String idToken;

    // Una columna en la base de datos.
    @Column(name = "TOKEN", nullable = false)
    private Blob token;

    // Una columna en la base de datos.
    @Column(name = "AUTHENTICATION", nullable = false)
    private Blob autenticacion;

}
