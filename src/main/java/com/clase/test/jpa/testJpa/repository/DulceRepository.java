package com.clase.test.jpa.testJpa.repository;

import com.clase.test.jpa.testJpa.model.Dulce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DulceRepository extends JpaRepository<Dulce, Long> {
}

