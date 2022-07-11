package com.example.biblioteca.servicios;

import com.example.biblioteca.entidades.Editorial;
import com.example.biblioteca.excepciones.MiException;
import com.example.biblioteca.repositorio.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional // se coloca para que ejecute un rollback en caso de lanzarse una 
    //exceptción y tengamos que deshacer cambios en la base de datos. requisito, debe lanzar exceptciones.
    public void crearEditorial(String nombre) throws MiException {
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }

    public List<Editorial> listarEditoriales() {
        List<Editorial> editoriales = new ArrayList();
        editoriales = editorialRepositorio.findAll();
        return editoriales;
    }

    @Transactional
    public void modificarEditorial(String nombre, String id) throws MiException {
        validar(nombre);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(id);
        Editorial editorial = new Editorial();
        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }
    }

    private void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
    }
}
