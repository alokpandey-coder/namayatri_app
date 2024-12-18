package com.namayatri.namayatri.Repository;

import com.namayatri.namayatri.Model.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Integer> {

    Optional<Area> findByName(String name);
}