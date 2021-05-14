package com.example.SpringExercise;

public class ProfileNotFoundException extends RuntimeException {

    ProfileNotFoundException(Long id){
        super("Could not find profile with id: " + id);
    }
}
