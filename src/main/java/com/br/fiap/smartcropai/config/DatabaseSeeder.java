// package com.br.fiap.smartcropai.config;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Configuration;

// import com.br.fiap.smartcropai.models.Solo;
// import com.br.fiap.smartcropai.models.Clima;
// import com.br.fiap.smartcropai.models.Usuario;
// import com.br.fiap.smartcropai.repository.SoloRepository;
// import com.br.fiap.smartcropai.repository.ClimaRepository;
// import com.br.fiap.smartcropai.repository.UsuarioRepository;

// @Configuration
// public class DatabaseSeeder implements CommandLineRunner {

//    @Autowired
//    SoloRepository soloRepository;

//    @Autowired
//    ClimaRepository climaRepository;

//    @Autowired 
//    UsuarioRepository usuarioRepository;

//    @Override
//    public void run(String... args) throws Exception {
//       soloRepository.saveAll(List.of(
//          Solo.builder().texturaDoSolo("Argiloso").phDoSolo(6.5).retencaoDeAgua("Alta").condutEletrica("Cobre").build(),
//          Solo.builder().texturaDoSolo("Arenoso").phDoSolo(4.8).retencaoDeAgua("Alta").condutEletrica("Cobre").build(),
//          Solo.builder().texturaDoSolo("Humoso").phDoSolo(8.8).retencaoDeAgua("Alta").condutEletrica("Cobre").build(),
//          Solo.builder().texturaDoSolo("Argiloso").phDoSolo(7).retencaoDeAgua("Baixa").condutEletrica("Cobre").build(),
//          Solo.builder().texturaDoSolo("Arenoso").phDoSolo(3.9).retencaoDeAgua("Baixa").condutEletrica("Cobre").build(),
//          Solo.builder().texturaDoSolo("Humoso").phDoSolo(10).retencaoDeAgua("Alta").condutEletrica("Cobre").build()
//       ));
//       climaRepository.saveAll(List.of(
//          Clima.builder().cep("02945-100").precipitacao("Baixa").umidade("Baixa").velDoVento("Ventos alisios").dirDoVento("Moderados a fortes").pressaoAtmosferica("Alta").build(),
//          Clima.builder().cep("69907-290").precipitacao("Alta").umidade("Baixa").velDoVento("Ventos alisios").dirDoVento("Moderados a baixo").pressaoAtmosferica("Baixo").build(),
//          Clima.builder().cep("69313-458").precipitacao("Baixa").umidade("Alta").velDoVento("Ventos alisios").dirDoVento("Fortes").pressaoAtmosferica("Alta").build(),
//          Clima.builder().cep("79073-245").precipitacao("Alta").umidade("Alta").velDoVento("Ventos alisios").dirDoVento("Baixos").pressaoAtmosferica("Baixo").build()
//       ));
//       usuarioRepository.save(Usuario.builder()
//       .nome("José Maria")
//       .cpf("360.121.618-32")
//       .email("jose.marinha@gmail.com")
//       .senha("$2a$12$pMH3uGhwRXAaEq21jmmqn.PzxykI/HJyVAXM6sIQlcQ/2emqevaWC")
//       .build()
//       );
//    }
   
// }
