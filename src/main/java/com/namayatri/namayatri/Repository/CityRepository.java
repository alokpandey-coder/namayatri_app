package com.namayatri.namayatri.Repository;

import com.namayatri.namayatri.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}