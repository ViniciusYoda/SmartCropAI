package com.br.fiap.smartcropai.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.br.fiap.smartcropai.models.Credencial;
import com.br.fiap.smartcropai.models.JwtToken;
import com.br.fiap.smartcropai.models.Usuario;
import com.br.fiap.smartcropai.repository.UsuarioRepository;

@Service
public class TokenJwtService {

   @Value("${jwt.secret}")
   String secret;

   @Autowired
   UsuarioRepository repository;

   public JwtToken generateToken(Credencial credencial) {
       Algorithm alg = Algorithm.HMAC256(secret);
       var token = JWT.create()
                   .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                   .withSubject(credencial.email())
                   .withIssuer("SmartCropAI")
                   .sign(alg)
                   ;

       return new JwtToken(token);
   }

   public Usuario validate(String token) {
       Algorithm alg = Algorithm.HMAC256(secret);
       var email = JWT.require(alg)
                   .withIssuer("SmartCropAI")
                   .build()
                   .verify(token)
                   .getSubject();
       
       return repository.findByEmail(email)
           .orElseThrow(() -> new RuntimeException("Token inválido"));
   }
}