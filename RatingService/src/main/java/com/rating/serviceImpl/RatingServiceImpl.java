package com.rating.serviceImpl;

import com.rating.entities.Rating;
import com.rating.repositories.RatingRepository;
import com.rating.services.RatingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating saveRatings(Rating rating) {
        log.info("Saving rating: {}", rating);
        Rating savedRating = ratingRepository.save(rating);
        log.info("Rating saved successfully: {}", savedRating);
        return savedRating;
    }

    @Override
    public List<Rating> getAllRatings() {
        log.info("Fetching all ratings");
        List<Rating> ratings = ratingRepository.findAll();
        log.info("Fetched {} ratings", ratings.size());
        return ratings;
    }

    @Override
    public List<Rating> getAllRatingsByUserId(String userId) {
        log.info("Fetching ratings for user ID: {}", userId);
        List<Rating> ratings = ratingRepository.findAllByUserId(userId);
        log.info("Fetched {} ratings for user ID: {}", ratings.size(), userId);
        return ratings;
    }

    @Override
    public List<Rating> getAllRatingsByHotelId(String hotelId) {
        log.info("Fetching ratings for hotel ID: {}", hotelId);
        List<Rating> ratings = ratingRepository.findAllByHotelId(hotelId);
        log.info("Fetched {} ratings for hotel ID: {}", ratings.size(), hotelId);
        return ratings;
    }
}
