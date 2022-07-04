package com.plantillabackend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plantillabackend.dtos.TblRolDTO;
import com.plantillabackend.services.RolService;

@RestController
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
}
