package com.namayatri.namayatri.Service;

import com.namayatri.namayatri.Model.Property;
import com.namayatri.namayatri.Model.Review;
import com.namayatri.namayatri.Model.User;
import com.namayatri.namayatri.Payload.ReviewDto;
import com.namayatri.namayatri.Repository.PropertyRepository;
import com.namayatri.namayatri.Repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    public ReviewService(ReviewRepository reviewRepository, PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

     Review mapToEntity(ReviewDto dto){

        return modelMapper.map(dto,Review.class);
      }

     ReviewDto mapToDto(Review review){
        return modelMapper.map(review, ReviewDto.class);
      }








    public ReviewDto addReview(ReviewDto reviewDto, int propertyId, User userId) {

        Review review = mapToEntity(reviewDto);
        Property property = propertyRepository.findById(propertyId).get();

        Review byPropertyAndUser = reviewRepository.findByPropertyAndUser(property, userId);

        if (byPropertyAndUser!= null) {
            throw new RuntimeException("You have already reviewed this property");
        }

        review.setProperty(property);
        review.setUser(userId);

        reviewRepository.save(review);

        ReviewDto reviewDtos = mapToDto(review);
        return reviewDtos;
    }

    public List<Review> viewMyReview(User user) {

       return reviewRepository.findByUserId(user);
    }
}
