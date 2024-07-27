package com.luv2code.sam.training.NewBookstore.controllers;

import com.luv2code.sam.training.NewBookstore.entities.User;
import com.luv2code.sam.training.NewBookstore.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "user")
public class UserController {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @GetMapping()
    public String hello(){
            return "hello";
    }

    @PostMapping(value = "/new-user", consumes = APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user) {

        User userToBeCreated = new User(user.getFirstname(), user.getLastname(), user.getEmail(), passwordEncoder.encode(user.getPassword()), LocalDateTime.now());
        userRepository.save(userToBeCreated);
        return "User created successfully!";
    }

    @GetMapping("/all-users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/all-users")
    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "All users deleted successfully!";
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        /*if (user != null) {
            return user;
        }
        return null;*/

        //return user != null ? user : null;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return "user deleted successfully";
        }
        throw new RuntimeException("User does not exist.");
    }

    @PutMapping("/{userId}")
    public String modifyUser(@PathVariable Long userId, @RequestBody User user) {
        User user1 = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user1.setFirstname(user.getFirstname());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        userRepository.save(user1);
        return "User details updated successfully";
    }

}
