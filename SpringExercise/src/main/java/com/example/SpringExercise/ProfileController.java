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
                .orElseThrow(() -> new ProfileNotFoundException(id));
    }

    @PostMapping("/user")
    Profile newProfile(@RequestBody Profile newProfile){
        return repository.save(newProfile);
    }

    @PatchMapping("/user/{id}")
    Profile updateProfile(@RequestBody Profile newProfile, @PathVariable Long id){
        return repository.findById(id)
                .map(profile -> {
                    profile.setName(newProfile.getName() == null ? profile.getName() : newProfile.getName());
                    profile.setEmail(newProfile.getEmail() == null ? profile.getEmail() : newProfile.getEmail());
                    profile.setPhone(newProfile.getPhone() == null ? profile.getPhone() : newProfile.getPhone());
                    profile.setCredit(newProfile.getCredit() == 0 ? profile.getCredit() : newProfile.getCredit());

                    return repository.save(profile);
                })
                .orElseThrow(() -> new ProfileNotFoundException(id));
    }

    @PutMapping("user/{id}")
    Profile addOrUpdateProfile(@RequestBody Profile newProfile, @PathVariable Long id){
        return repository.findById(id)
                .map(profile -> {
                    profile.setName(newProfile.getName() == null ? profile.getName() : newProfile.getName());
                    profile.setEmail(newProfile.getEmail() == null ? profile.getEmail() : newProfile.getEmail());
                    profile.setPhone(newProfile.getPhone() == null ? profile.getPhone() : newProfile.getPhone());
                    profile.setCredit(newProfile.getCredit() == 0 ? profile.getCredit() : newProfile.getCredit());

                    return repository.save(profile);
                })
                .orElseGet(() -> {
                    newProfile.setId(id);
                    return repository.save(newProfile);
                });
    }
}
