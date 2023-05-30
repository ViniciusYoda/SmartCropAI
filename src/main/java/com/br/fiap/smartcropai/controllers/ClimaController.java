package com.br.fiap.smartcropai.controllers;


import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


import com.br.fiap.smartcropai.exceptions.RestNotFoundException;
import com.br.fiap.smartcropai.models.Clima;
import com.br.fiap.smartcropai.repository.ClimaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/clima")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "clima")
public class ClimaController {

   @Autowired
   ClimaRepository repository;

   @Autowired PagedResourcesAssembler<Clima> assembler;

   @GetMapping
   @Operation(
      summary = "Listar climas",
      description = "Endpoint que retorna todos os climas registrados"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "climas listados com sucesso"),
      @ApiResponse(responseCode = "400", description = "clima não encontrados")
   })
   public PagedModel<EntityModel<Clima>> index(@ParameterObject @PageableDefault(size = 5) Pageable pageable) {
      Page<Clima> climas = repository.findAll(pageable);
   
      PagedModel<EntityModel<Clima>> pagedModel = assembler.toModel(climas);
   
      pagedModel.forEach(clima -> clima.add(linkTo(ClimaController.class).slash(clima.getContent().getId()).withSelfRel()));
      pagedModel.add(linkTo(ClimaController.class).withSelfRel());
   
      return pagedModel;
   }

   @PostMapping
   @Operation(
      summary = "Criar clima",
      description = "Endpoint que cria o clima"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "201", description = "clima criado com sucesso"),
      @ApiResponse(responseCode = "400", description = "clima não criado")
   })
   public ResponseEntity<Object> create(@RequestBody @Valid Clima clima) {
      log.info("Cadastro do clima " + clima);
      repository.save(clima);
      return ResponseEntity.status(HttpStatus.CREATED).body(clima.toEntityModel());
   }

   @GetMapping("{id}")
   @Operation(
      summary = "Detalhar clima",
      description = "Endpoint que recebe um id e retorna os dados do clima. O id deve ser único para cada produto"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "dados retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "não existe o clima com o id informado")
   })
   public EntityModel<Clima> show(@PathVariable Long id){
      log.info("Detalhe do clima " + id);
      var clima = repository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("solo não encontrado"));

      return clima.toEntityModel();
   }
   
   @DeleteMapping("{id}")
   @Operation(
      summary = "Deletar clima",
      description = "Endpoint que recebe um id e deleta o clima. O id deve ser único para cada solo"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "clima apagado com sucesso"),
      @ApiResponse(responseCode = "400", description = "não existe clima com o id informado")
   })
   public ResponseEntity<Clima> destroy(@PathVariable Long id){
      log.info("Apagando o clima" + id);
      var clima = repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, clima não encontrado"));

      repository.delete(clima);   

      return ResponseEntity.noContent().build();
   }

   @PutMapping("{id}")
   @Operation(
      summary = "Atualizar clima",
      description = "Endpoint que recebe um id e retorna os dados do clima atualizado. O id deve ser único para cada solo"
   )
   public EntityModel<Clima> update(@PathVariable Long id, @RequestBody @Valid Clima clima){
      log.info("Solo atualizado " + id);
      repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, solo não encontrado"));

      clima.setId(id);
      repository.save(clima);

      return clima.toEntityModel();

   }
   
}
