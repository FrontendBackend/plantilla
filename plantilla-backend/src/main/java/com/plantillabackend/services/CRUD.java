package com.plantillabackend.services;

import java.util.List;

public interface CRUD<T, ID> {
    
    /**
     * Esta función toma un objeto de tipo T y devuelve un objeto de tipo T.
     * 
     * @param t El objeto a registrar.
     * @return El objeto que se pasó.
     */
    T registrar(T t) throws Exception;

    /**
     * Modificar toma una T y devuelve una T, y podría arrojar una excepción.
     * 
     * @param t El objeto a modificar.
     * @return El objeto que fue modificado.
     */
    T modificar(T t) throws Exception;

    /**
     * Esta función devuelve una lista de objetos T y puede generar una excepción.
     * 
     * @return Una lista de objetos de tipo T.
     */
    List<T> listar() throws Exception;

    /**
     * > Esta función devuelve una lista de objetos de tipo T, dado un ID de tipo ID
     * 
     * @param id El id del objeto a buscar.
     * @return El objeto que se encontró.
     */
    T listarPorId(ID id) throws Exception;

    /**
     * Esta función elimina el objeto con la ID dada de la base de datos.
     * 
     * @param id El id del objeto que se va a eliminar.
     */
    void eliminar(ID id) throws Exception;
}
