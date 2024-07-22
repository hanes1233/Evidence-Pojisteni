package com.example.evidencepojisteni.repositories;

import com.example.evidencepojisteni.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findByUsersId(Long id);
}
