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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.br.fiap.smartcropai.exceptions.RestNotFoundException;
import com.br.fiap.smartcropai.models.Solo;
import com.br.fiap.smartcropai.repository.SoloRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/solo")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "solo")
public class SoloController {

   @Autowired
   SoloRepository repository;

   @Autowired
   PagedResourcesAssembler<Object> assembler;

   @GetMapping
   @Operation(
      summary = "Listar solos",
      description = "Endpoint que retorna todos os solos registrado"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "solos listado com sucesso"),
      @ApiResponse(responseCode = "400", description = "solos não encontrado")
   })
   public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
      Page<Solo> solos = (busca == null)?
      repository.findAll(pageable):
      repository.findByDescricaoContaining(busca, pageable);

      return assembler.toModel(solos.map(Solo::toEntityModel));
   }

   @PostMapping
   @Operation(
      summary = "Criar solo",
      description = "Endpoint que cria o solo"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "201", description = "solo criado com sucesso"),
      @ApiResponse(responseCode = "400", description = "solo não criado")
   })
   public ResponseEntity<Object> create(@RequestBody @Valid Solo solo) {
      log.info("Cadastro do solo " + solo);
      repository.save(solo);
      return ResponseEntity.status(HttpStatus.CREATED).body(solo.toEntityModel());
   }

   @GetMapping("{id}")
   @Operation(
      summary = "Detalhar solo",
      description = "Endpoint que recebe um id e retorna os dados do solo. O id deve ser único para cada produto"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "dados retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "não existe o solo com o id informado")
   })
   public EntityModel<Solo> show(@PathVariable Long id){
      log.info("Detalhe do solo " + id);
      var solo = repository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("solo não encontrado"));

      return solo.toEntityModel();
   }

   
   @DeleteMapping("{id}")
   @Operation(
      summary = "Deletar solo",
      description = "Endpoint que recebe um id e deleta o solo. O id deve ser único para cada solo"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "solo apagado com sucesso"),
      @ApiResponse(responseCode = "400", description = "não existe solo com o id informado")
   })
   public ResponseEntity<Solo> destroy(@PathVariable Long id){
      log.info("Apagando o solo" + id);
      var solo = repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, solo não encontrado"));

      repository.delete(solo);   

      return ResponseEntity.noContent().build();
   }

   @PutMapping("{id}")
   @Operation(
      summary = "Atualizar solo",
      description = "Endpoint que recebe um id e retorna os dados do solo atualizado. O id deve ser único para cada solo"
   )
   public EntityModel<Solo> update(@PathVariable Long id, @RequestBody @Valid Solo solo){
      log.info("Solo atualizado " + id);
      repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, solo não encontrado"));

      solo.setId(id);
      repository.save(solo);

      return solo.toEntityModel();

   }
   
}
