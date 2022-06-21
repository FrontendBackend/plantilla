package com.plantillabackend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plantillabackend.dtos.TblRolDTO;
import com.plantillabackend.models.repository.RolRepository;
import com.plantillabackend.services.RolService;

@Service
@Transactional(readOnly = true)
public class RolServiceImpl implements RolService {
    
    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<TblRolDTO> listarRol() {
        return rolRepository.listarRoles();
    }
    
}
