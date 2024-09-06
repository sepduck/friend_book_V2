package com.friendbook.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenderService {
    List<Gender> listGender();

    Gender addGender(GenderDTO genderDTO);

    Gender updateGender(long genderId, GenderDTO genderDTO);

    void deleteGender(long genderId);

    Gender findGenderById(long genderId);


}
