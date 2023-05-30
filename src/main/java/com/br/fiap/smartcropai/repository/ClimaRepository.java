package com.br.fiap.smartcropai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fiap.smartcropai.models.Clima;

public interface ClimaRepository extends JpaRepository<Clima, Long> {
   
   
}
