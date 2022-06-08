package com.plantillabackend.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "TBL_RESET_TOKEN")
@NoArgsConstructor
public class TblResetToken implements Serializable {

    // Crear una secuencia en la base de datos.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_RESET_TOKEN_SEQ")
    @SequenceGenerator(name = "TBL_RESET_TOKEN_SEQ", sequenceName = "TBL_RESET_TOKEN_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    // Crear una columna en la base de datos llamada token que sea única y no pueda
    // ser nula.
    @Column(nullable = false, unique = true)
    private String token;

    // Crear una relación entre las tablas TblResetToken y TblUsuario.
    @OneToOne(targetEntity = TblUsuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "ID_USUARIO")
    private TblUsuario user;

    // Creando una columna en la base de datos llamada caducidad que no puede ser
    // nula.
    @Column(nullable = false)
    private LocalDateTime expiracion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TblUsuario getUser() {
        return user;
    }

    public void setUser(TblUsuario user) {
        this.user = user;
    }

    public LocalDateTime getExpiracion() {
        return expiracion;
    }

    /**
     * Esta función establece la fecha de caducidad del token.
     * 
     * @param expiracion fecha de caducidad
     */
    public void setExpiracion(LocalDateTime expiracion) {
        this.expiracion = expiracion;
    }

    /**
     * Establece la fecha de caducidad del objeto a la fecha actual más la cantidad
     * de minutos
     * transcurridos como parámetro
     * 
     * @param minutos el número de minutos a añadir a la hora actual
     */
    public void setExpiracion(int minutos) {
        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime exp = hoy.plusMinutes(minutos);
        this.expiracion = exp;
    }

    /**
     * Comprueba si la hora actual es posterior a la hora de caducidad.
     * 
     * @return Un valor booleano.
     */
    public boolean estaExpirado() {
        return LocalDateTime.now().isAfter(this.expiracion);
    }
}
