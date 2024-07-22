package com.example.evidencepojisteni.repositories;

import com.example.evidencepojisteni.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance,Long> {
    List<Insurance> findAllByUserId(Long id);
}
