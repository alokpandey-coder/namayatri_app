package com.namayatri.namayatri.Repository;

import com.namayatri.namayatri.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {


    Optional<Property> findByName(String name);



    @Query("select p from Property p join p.country c join p.city cy join p.area a  where c.name=:searchName or cy.name=:searchName or a.name=:searchName")
    List<Property> searchProperty(@Param("searchName") String searchName);

}