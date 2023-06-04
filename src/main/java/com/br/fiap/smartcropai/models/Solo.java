package com.br.fiap.smartcropai.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.br.fiap.smartcropai.controllers.SoloController;

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
@Table(name = "Solo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solo {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank
   @Size(min = 6, max = 12)
   private String texturaDoSolo;

   @NotNull
   @Min(value = 1, message = "deve ser positivo")
   private double phDoSolo;

   @NotBlank
   @Size(min = 6, max = 12)
   private String retencaoDeAgua;

   @NotBlank
   @Size(min = 6, max = 12)
   private String condutEletrica;

   public EntityModel<Solo> toEntityModel() {
      Link selfLink = linkTo(SoloController.class).slash(id).withSelfRel();
      Link deleteLink = linkTo(SoloController.class).slash(id).withRel("delete");
      Link allLink = linkTo(SoloController.class).withRel("all");

      return EntityModel.of(
         this,
         selfLink,
         deleteLink,
         allLink
      );
   }

}
