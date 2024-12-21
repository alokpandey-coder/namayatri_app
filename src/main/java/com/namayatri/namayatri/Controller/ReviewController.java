package com.namayatri.namayatri.Controller;

import com.namayatri.namayatri.Model.Review;
import com.namayatri.namayatri.Model.User;
import com.namayatri.namayatri.Payload.ReviewDto;
import com.namayatri.namayatri.Service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //http://localhost:8080/api/review/addReview

    @PostMapping("/addReview")
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto reviewDto,
                                               @RequestParam int propertyId,
                                               @AuthenticationPrincipal User userId
    ){
        ReviewDto dto = reviewService.addReview(reviewDto,propertyId,userId);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/review/myReview

    @GetMapping("/myReview")
    public ResponseEntity<List<Review>> viewMyReview(@AuthenticationPrincipal User user){

        List<Review> review =reviewService.viewMyReview(user);

        return new ResponseEntity<>(review,HttpStatus.OK);
    }
}

