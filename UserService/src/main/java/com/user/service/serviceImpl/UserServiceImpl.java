package com.user.service.serviceImpl;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ApiException;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.externalService.HotelService;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService; // binding hotelService here for using feign client to get the hotels information


    @Override
    public User saveUser(User user) {
        log.info("Save method called for user details {}", user);
        String randomUserId = UUID.randomUUID().toString();
        log.info("Generated random userId is {}",randomUserId);
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users method calling");
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        log.info("Calling update user method for the userId {}",user.getUserId());
        if (Objects.isNull(user.getUserId())){
            throw new ApiException("Provide userId to update the user");
        } else {
            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public void deleteUserById(String userId) {
        log.info("Calling the delete user method for userId {}",userId);
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(String userId) {
        log.info("Getting user with user Id method calling for userId {}",userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user found with the userId " + userId));

        log.info("User fetched successfully, fetching the ratings of this user");
        // fetch users all ratings by using the restTemplate

        Rating[] ratings = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
        log.info("Ratings with the userId {} fetched successfully, fetching hotels information.",userId);

        // fetch hotels information by using the restTemplate
        List<Rating> ratingList = Arrays.stream(ratings).map(rating -> {
//            Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
