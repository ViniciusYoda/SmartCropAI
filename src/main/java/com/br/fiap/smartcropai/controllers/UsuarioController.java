package com.br.fiap.smartcropai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.fiap.smartcropai.models.Credencial;
import com.br.fiap.smartcropai.models.Usuario;
import com.br.fiap.smartcropai.repository.UsuarioRepository;
import com.br.fiap.smartcropai.service.TokenJwtService;
import jakarta.validation.Valid;

@RestController
public class UsuarioController {
   
   
   @Autowired
   UsuarioRepository repository;

   @Autowired
   AuthenticationManager manager;

   @Autowired
   PasswordEncoder encoder;

   @Autowired
   TokenJwtService tokenJwtService;

   @PostMapping("/api/cadastrar")
   public ResponseEntity<Usuario> registrar(@RequestBody @Valid Usuario usuario) {
      usuario.setSenha(encoder.encode(usuario.getSenha()));
      repository.save(usuario);
      return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
   }

   @PostMapping("/api/login")
   public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial) {
      manager.authenticate(credencial.toAuthentication());
      var token = tokenJwtService.generateToken(credencial);
      return ResponseEntity.ok(token);
   }
}
