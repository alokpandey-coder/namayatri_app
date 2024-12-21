package com.namayatri.namayatri.Repository;

import com.namayatri.namayatri.Model.Review;
import com.namayatri.namayatri.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

     //Get Review From Particular User

    List<Review> findByUserId(User user);
}