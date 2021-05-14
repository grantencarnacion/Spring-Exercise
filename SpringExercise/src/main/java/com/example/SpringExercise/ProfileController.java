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
    List<Profile> getProfiles(){
        return repository.findAll();
    }

    @GetMapping("/user/{id}")
    Profile getProfile(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("non existent user id" + id));
    }

    @PostMapping("/user")
    Profile newProfile(@RequestBody Profile newProfile){
        return repository.save(newProfile);
    }

    @PatchMapping("/user/{id}")
    Profile updateProfile(@RequestBody Profile newProfile, @PathVariable Long id){
        return repository.findById(id)
                .map(profile -> {
                    profile.setName(newProfile.getName());
                    profile.setEmail(newProfile.getEmail());
                    profile.setPhone(newProfile.getPhone());
                    profile.setCredit(newProfile.getCredit());

                    return repository.save(profile);
                })
                .orElseThrow(() -> new RuntimeException("non existent user id" + id));
    }

}
