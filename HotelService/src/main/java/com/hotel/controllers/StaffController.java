package com.hotel.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/staff")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getAllStaffsNames() {
        List<String> namesList = Arrays.asList("Ram", "Shyam", "Sita", "Lakshman");
        return new ResponseEntity<>(namesList, HttpStatus.OK);
    }
}
