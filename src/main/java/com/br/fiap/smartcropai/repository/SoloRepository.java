package com.br.fiap.smartcropai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.fiap.smartcropai.models.Solo;

public interface SoloRepository extends JpaRepository<Solo, Long> {
   
   Page<Solo> findByDescricaoContaining(String busca, Pageable pageable);
}
