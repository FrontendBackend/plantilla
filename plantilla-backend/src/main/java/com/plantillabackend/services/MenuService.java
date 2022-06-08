package com.plantillabackend.services;

import java.util.List;

import com.plantillabackend.dtos.TblMenuDTO;
import com.plantillabackend.models.entity.TblMenu;

public interface MenuService extends CRUD<TblMenu, Long>{
    
    /**
     * Devuelve una lista de objetos TblMenu para un nombre de usuario determinado.
     * 
     * @param nombre El nombre del usuario.
     * @return Lista<TblMenu>
     */
    List<TblMenu> listarMenuPorUsuario(String nombre);

    /**
     * Devuelve una lista de objetos TblMenuDTO.
     * 
     * @return Lista<TblMenuDTO>
     */
    List<TblMenuDTO> listarMenu();
}
