package com.example.find_a_film_API.Controller;

import com.example.find_a_film_API.Models.User;
import com.example.find_a_film_API.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;


    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable (required = true)Long userId) {
        return userRepository.findById(userId);
    }

    @PostMapping()
    public String addUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return String.format("User email '%s' already exists in the database", user.getEmail());
        }
        user.setUsername(user.getFirstName() + "." + user.getLastName());
        userRepository.save(user);
        return String.format("User ID %d was added to database:\n First name : %s\nLast name: %s\nEmail: %s\nUsername: %s", user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername());
    }

    @DeleteMapping("{userId}")
    public String deleteById(@PathVariable(required = true) Long userId) {
        User target = userRepository.findById(userId).get();
        userRepository.delete(target);
        return String.format("User ID %d was removed\nUsername: %s\nEmail: %s", userId, target.getUsername(), target.getEmail());
    }

    //Potentially temporary logic to enable adding of test users
    @PostMapping("/populate")
    public String addTestUsers() {
        if(userRepository.findByEmail("sally-draycott@hotmail.co.uk") != null) {
            return "Test users have already been added to the database";
        }
        List<User> testUsers = new ArrayList<>();
        User sally = new User("Sally", "Draycott", "sally-draycott@hotmail.co.uk");
        sally.setUsername(sally.getFirstName() + "." + sally.getLastName());
        testUsers.add(sally);
        User bonnie = new User("Bonnie", "Cruz", "");
        bonnie.setUsername(bonnie.getFirstName() + "." + bonnie.getLastName());
        testUsers.add(bonnie);
        User karl = new User("Karl", "Campbell", "karlcampbell100@gmail.com");
        karl.setUsername(karl.getFirstName() + "." + karl.getLastName());
        testUsers.add(karl);

        userRepository.saveAll(testUsers);
        return "Test users were added to database";
    }

}
