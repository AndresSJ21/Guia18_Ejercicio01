/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.biblioteca.servicios;

import com.example.biblioteca.entidades.Autor;
import com.example.biblioteca.excepciones.MiException;
import com.example.biblioteca.repositorio.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);

        autorRepositorio.save(autor);
    }

    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList();
        autores = autorRepositorio.findAll();
        return autores;
    }

    @Transactional
    public void modificarAutor(String nombre, String id) throws MiException {
        validar(nombre);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(id);
        if (respuestaAutor.isPresent()) {
            Autor autor = respuestaAutor.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }
    }
    
    private void validar(String nombre) throws MiException{
        if (nombre.isEmpty() || nombre == null){
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
    }
}