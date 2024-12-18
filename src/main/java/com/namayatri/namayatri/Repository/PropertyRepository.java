package com.namayatri.namayatri.Repository;

import com.namayatri.namayatri.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {


    Optional<Property> findByName(String name);

}