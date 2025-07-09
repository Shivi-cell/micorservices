package com.user.service.controllers;

import com.user.service.entities.User;
import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<User> updateUserDetails(@RequestBody User user) {
        User user1 = userService.updateUser(user);
        return ResponseEntity.ok(user1);
    }

    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * maintaining the retry count so that we can understand the retry module of resilience4j
     */
    int retryCount = 1;

    @GetMapping(path = "/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//    @Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "userHotelRateLimiter", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        log.info("Retry count is : {}", retryCount);
        retryCount++;
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * Creating method for circuit breaker/retry module
     */
    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception e) {
        log.info("Fallback is executing because service is down. Exception msg :  {}",e.getMessage());
        User user = User.builder().name("dummyName").email("dummy@gamil.com").about("This is dummy user").userId("13462").build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

}
