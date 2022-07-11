
package com.example.biblioteca.repositorio;

import com.example.biblioteca.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutorRepositorio extends JpaRepository <Autor, String>{
    
}
