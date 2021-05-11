package com.example.SpringExercise;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {

    private final ProfileRepository repository;

    public ProfileController(ProfileRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/user")
    List<Profile> all(){
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    Profile one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("non existent user id" + id));
    }

    @PostMapping("/user")
    Profile newProfile(@RequestBody Profile newProfile){
        return repository.save(newProfile);
    }
}
