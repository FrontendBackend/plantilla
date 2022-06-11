package com.plantillabackend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plantillabackend.dtos.TblUsuarioDTO;
import com.plantillabackend.models.entity.TblUsuario;
import com.plantillabackend.services.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Devuelve una lista de usuarios en la base de datos.
     * 
     * @return Una lista de objetos TblUsuarioDTO.
     */
    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<TblUsuarioDTO>> listarUsuarios() {

        List<TblUsuarioDTO> lTblPortafolioDTO = this.usuarioService.listarUsuarios();
        return new ResponseEntity<List<TblUsuarioDTO>>(lTblPortafolioDTO, HttpStatus.OK);
    }

    /**
     * Recibe un objeto JSON, lo valida y luego lo guarda en la base de datos.
     * 
     * @param tblUsuarioDTO Este es el objeto que estoy tratando de salvar.
     * @param resultadoValidacion Resultado de enlace
     * @return Un objeto ResponseEntity.
     */
    @PostMapping("/crearUsuario")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody TblUsuarioDTO tblUsuarioDTO,
            BindingResult resultadoValidacion) throws Exception {

        TblUsuarioDTO tblUsuarioDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear un usuario");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            tblUsuarioDTOCreado = this.usuarioService.crearUsuario(tblUsuarioDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear un usuario");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El usuario ha sido creada");
        respuesta.put("tblUsuarioDTOCreado", tblUsuarioDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Obtiene un usuario por id y lo devuelve.
     * 
     * @param idUsuario 1
     * @return Un objeto ResponseEntity.
     */
    @GetMapping("/obtenerConfiguracionesGenerales/{idUsuario}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idUsuario) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        TblUsuarioDTO tblUsuarioDTO = null;

        try {
            tblUsuarioDTO = this.usuarioService.obtenerUsuarioPorId(idUsuario);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el usuario");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tblUsuarioDTO == null) {
            mensaje = String.format("El usuario con el id: %d no existe en la base de datos", idUsuario);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El usuario ha sido recuperado");
        respuesta.put("tblUsuarioDTO", tblUsuarioDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }

    /**
     * La función recibe un objeto JSON, lo valida y luego actualiza la base de datos con los nuevos
     * valores
     * 
     * @param tblUsuarioDTO Este es el objeto que estoy enviando desde la interfaz.
     * @param resultadoValidacion Este es el resultado de la validación del objeto que se envía en el
     * cuerpo de la solicitud.
     * @return Un objeto ResponseEntity.
     * Pregunta: ¿Qué es un objeto ResponseEntity?
     * Respuesta: un objeto ResponseEntity es un contenedor para la respuesta HTTP.
     * Pregunta: ¿Qué es la respuesta HTTP?
     * Respuesta: La respuesta HTTP son los datos que se devuelven al cliente.
     * Pregunta: ¿Qué es el cliente?
     * Respuesta: El cliente es el navegador.
     * Pregunta: ¿Qué es el navegador?
     */
    @PutMapping("/modificarPortafolio")
    public ResponseEntity<?> modificarPortafolio(@Valid @RequestBody TblUsuarioDTO tblUsuarioDTO, BindingResult resultadoValidacion) throws Exception {

        TblUsuario tblUsuarioActual = null;
        TblUsuarioDTO tblUsuarioDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el usuario");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            tblUsuarioActual = this.usuarioService.findById(tblUsuarioDTO.getIdUsuario());

            if (tblUsuarioActual == null) {
                mensaje = String.format("el usuario %s que intenta actualizar no existe en la base de datos", tblUsuarioDTO.getIdUsuario());
                
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el usuario a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            tblUsuarioDTOModificada = this.usuarioService.modificarUsuario(tblUsuarioDTO, tblUsuarioActual, this.obtenerAuditoria());

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el usuario");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "el usuario ha sido modificada");
        respuesta.put("tblUsuarioDTOModificada", tblUsuarioDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
}
