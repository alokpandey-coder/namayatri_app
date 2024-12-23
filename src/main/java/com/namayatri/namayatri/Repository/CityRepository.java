package com.namayatri.namayatri.Repository;

import com.namayatri.namayatri.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {


    Optional<City> findByName(String name);
}