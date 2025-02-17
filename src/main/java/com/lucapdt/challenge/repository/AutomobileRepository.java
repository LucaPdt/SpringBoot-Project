package com.lucapdt.challenge.repository;

import com.lucapdt.challenge.model.entity.Automobile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomobileRepository extends JpaRepository<Automobile, Integer> {

    Page<Automobile> findByMarca(String marca, Pageable pageable);

    Page<Automobile> findByPrezzoBetween(double prezzoMin, double prezzoMax, Pageable pageable);

    Page<Automobile> findByStato(Automobile.StatoAuto stato, Pageable pageable);
}