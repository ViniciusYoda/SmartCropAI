package com.br.fiap.smartcropai.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.br.fiap.smartcropai.models.Solo;
import com.br.fiap.smartcropai.models.Clima;
import com.br.fiap.smartcropai.models.Usuario;
import com.br.fiap.smartcropai.repository.SoloRepository;
import com.br.fiap.smartcropai.repository.ClimaRepository;
import com.br.fiap.smartcropai.repository.UsuarioRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

   @Autowired
   SoloRepository soloRepository;

   @Autowired
   ClimaRepository climaRepository;

   @Autowired 
   UsuarioRepository usuarioRepository;

   @Override
   public void run(String... args) throws Exception {
      soloRepository.save(Solo.builder()
      .texturaDoSolo("Argiloso")
      .phDoSolo(6.5)
      .retencaoDeAgua("Alta")
      .condutEletrica("Cobre")
      .build()
      );
      climaRepository.save(Clima.builder()
      .cep(12345678)
      .precipitacao(15.2)
      .umidade(68)
      .velDoVento(25)
      .dirDoVento("Noroeste")
      .pressaoAtmosferica(1013)
      .build()
      );
      usuarioRepository.save(Usuario.builder()
      .nome("José Maria")
      .cpf("12345678910")
      .email("jose.marinha@gmail.com")
      .senha("$2a$12$pMH3uGhwRXAaEq21jmmqn.PzxykI/HJyVAXM6sIQlcQ/2emqevaWC")
      .build()
      );
   }
   
}
