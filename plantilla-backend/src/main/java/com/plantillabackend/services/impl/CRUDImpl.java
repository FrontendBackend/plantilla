package com.plantillabackend.services.impl;

import java.util.List;

import com.plantillabackend.models.repository.GenericRepository;
import com.plantillabackend.services.CRUD;

public abstract class CRUDImpl<T, ID> implements CRUD<T, ID> {

    /**
     * Devuelve el repositorio que está utilizando este servicio.
     * 
     * @return Un repositorio genérico.
     */
    protected abstract GenericRepository<T, ID> getRepository();

    /**
     * Toma un objeto de tipo T, lo guarda en la base de datos y devuelve el objeto
     * guardado
     * 
     * @param t El objeto a guardar
     * @return El objeto que se guardó.
     */
    @Override
    public T registrar(T t) throws Exception {
        return getRepository().save(t);
    }

    /**
     * Toma un objeto de tipo T y devuelve un objeto de tipo T
     * 
     * @param t El objeto a modificar
     * @return El objeto que fue modificado.
     */
    @Override
    public T modificar(T t) throws Exception {
        return getRepository().save(t);
    }

    /**
     * Devuelve una lista de todos los objetos en la base de datos.
     * 
     * @return Una lista de objetos de tipo T.
     */
    @Override
    public List<T> listar() throws Exception {
        return getRepository().findAll();
    }

    /**
     * Devuelve el objeto con el id dado, o nulo si no existe
     * 
     * @param id El id del objeto a recuperar.
     * @return El objeto de la clase que se está utilizando.
     */
    @Override
    public T listarPorId(ID id) throws Exception {
        return getRepository().findById(id).orElse(null);
    }

    /**
     * Esta función elimina un objeto de la base de datos por su ID.
     * 
     * @param id El id de la entidad a eliminar.
     */
    @Override
    public void eliminar(ID id) throws Exception {
        getRepository().deleteById(id);
    }

}
