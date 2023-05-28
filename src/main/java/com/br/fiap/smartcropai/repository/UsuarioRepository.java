package com.br.fiap.smartcropai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fiap.smartcropai.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   
   Optional<Usuario> findByEmail(String email);
   
}