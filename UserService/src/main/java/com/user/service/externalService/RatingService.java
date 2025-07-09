package com.user.service.externalService;

import com.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping(path = "/ratings/save")
    Rating saveRating(Rating rating);

    @PutMapping(path = "/ratings/update")
    Rating updateRating(Rating rating);
}
