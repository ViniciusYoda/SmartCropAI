package com.br.fiap.smartcropai.models;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import com.br.fiap.smartcropai.controllers.SoloController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solo {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank
   @Size(min = 10, max = 20)
   private String texturaDoSolo;

   @NotNull
   @Min(value = 1, message = "deve ser positivo")
   private double phDoSolo;

   @NotBlank
   @Size(min = 10, max = 20)
   private String retencaoDeAgua;

   @NotNull
   @Min(value = 0, message = "deve ser positivo")
   private double condutEletrica;

   public EntityModel<Solo> toEntityModel(){
      return EntityModel.of(
         this,
         linkTo(methodOn(SoloController.class).show(id)).withSelfRel(),
         linkTo(methodOn(SoloController.class).destroy(id)).withRel("delete"),
         linkTo(methodOn(SoloController.class).index(null, null)).withRel("all")
      );
   }

}
