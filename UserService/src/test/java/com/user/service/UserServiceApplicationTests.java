package com.user.service;

import com.user.service.entities.Rating;
import com.user.service.externalService.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	/*@Test
	public void createRating() {
		Rating rating = new Rating();
		rating.setRating(6); rating.setUserId("d23db056-8dee-4bef-bc57-7d7192a08405"); rating.setHotelId("b594c408-06c7-4a30-b001-7768d5bc39ab");
		rating.setFeedback("This is kam chalu hotel");
		ratingService.saveRating(rating);
		System.out.println("Rating created "+rating);
	}*/

	/*@Test
	public void updateRating() {
		Rating rating = new Rating();
		rating.setRating(6); rating.setUserId("d23db056-8dee-4bef-bc57-7d7192a08405"); rating.setHotelId("b594c408-06c7-4a30-b001-7768d5bc39ab");
		rating.setFeedback("This is kam chalu hotel 2.o");
		rating.setRatingId("66b112a2bb4c9d061df54425");
		ratingService.updateRating(rating);
		System.out.println("Rating update successfully");
	}*/
}
