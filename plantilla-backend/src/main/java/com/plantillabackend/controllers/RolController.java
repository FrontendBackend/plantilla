package com.plantillabackend.controllers;

import java.util.ArrayList;
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

import com.plantillabackend.dtos.TblRolDTO;
import com.plantillabackend.models.entity.TblRol;
import com.plantillabackend.services.RolService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/roles")
public class RolController extends BaseController {
    
    @Autowired
    private RolService rolService;

    /**
     * Me permite listar los roles
     * @return
     * @throws Exception
     */
    @GetMapping("/listarRoles")
    public ResponseEntity<List<TblRolDTO>> listarRoles() throws Exception {
        List<TblRolDTO> lLegRolDTO = new ArrayList<>();
        lLegRolDTO = rolService.listarRol();
        return new ResponseEntity<List<TblRolDTO>>(lLegRolDTO, HttpStatus.OK);
    }

    @PostMapping("/crearRol")
    public ResponseEntity<?> crearRol(@Valid @RequestBody TblRolDTO tblRolDTO,
            BindingResult resultadoValidacion) throws Exception {

        TblRolDTO tblRolDTOCreado = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear un rol");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            tblRolDTOCreado = this.rolService.crearRol(tblRolDTO, this.obtenerAuditoria());
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear un rol");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "El usuario ha sido creada");
        respuesta.put("tblRolDTOCreado", tblRolDTOCreado);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping("/modificarRol")
    public ResponseEntity<?> modificarRol(@Valid @RequestBody TblRolDTO tblRolDTO, BindingResult resultadoValidacion) throws Exception {

        TblRol tblRolActual = null;
        TblRolDTO tblRolDTOModificada = null;
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el rol");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
            tblRolActual = this.rolService.findById(tblRolDTO.getIdRol());

            if (tblRolActual == null) {
                mensaje = String.format("el rol %s que intenta actualizar no existe en la base de datos", tblRolDTO.getIdRol());
                
                respuesta.put("mensaje", mensaje);

                return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error intentar recuperar el rol a actualizar");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            tblRolDTOModificada = this.rolService.modificarRol(tblRolDTO, tblRolActual, this.obtenerAuditoria());

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar modificar el rol");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "el rol ha sido modificada");
        respuesta.put("tblRolDTOModificada", tblRolDTOModificada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }

    
    @GetMapping("/obtenerConfiguracionesGenerales/{idRol}")
    public ResponseEntity<?> obtenerConfiguracionesGenerales(@PathVariable Long idRol) {
        String mensaje;
        Map<String, Object> respuesta = new HashMap<>();

        TblRolDTO tblRolDTO = null;

        try {
            tblRolDTO = this.rolService.obtenerRoloPorId(idRol);
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar recuperar el portafolio");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tblRolDTO == null) {
            mensaje = String.format("El rol con el id: %d no existe en la base de datos", idRol);
            respuesta.put("mensaje", mensaje);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NO_CONTENT);
        }

        respuesta.put("mensaje", "El rol ha sido recuperado");
        respuesta.put("tblRolDTO", tblRolDTO);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
}
