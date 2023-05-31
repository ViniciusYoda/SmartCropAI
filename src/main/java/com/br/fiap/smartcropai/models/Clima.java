package com.br.fiap.smartcropai.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.br.fiap.smartcropai.controllers.ClimaController;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clima {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotNull
   @Min(value = 8, message = "deve ser positivo")
   private double cep;

   @NotNull
   @Min(value = 1, message = "deve ser positivo")
   private double precipitacao;

   @NotNull
   @Min(value = 1, message = "deve ser positivo")
   private double umidade;

   @NotNull
   @Min(value = 1, message = "deve ser positivo")
   private double velDoVento;

   @NotBlank
   @Size(min = 6, max = 20)
   private String dirDoVento;

   @NotNull
   @Min(value = 1, message = "deve ser positivo")
   private double pressaoAtmosferica;

   public EntityModel<Clima> toEntityModel() {
      Link selfLink = linkTo(ClimaController.class).slash(id).withSelfRel();
      Link deleteLink = linkTo(ClimaController.class).slash(id).withRel("delete");
      Link allLink = linkTo(ClimaController.class).withRel("all");

      return EntityModel.of(
         this,
         selfLink,
         deleteLink,
         allLink
      );
   }

}
