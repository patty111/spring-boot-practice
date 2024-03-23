package org.example.demo.Controllers;

import jakarta.persistence.EntityNotFoundException;
import org.example.demo.Entities.User;
import org.example.demo.Entities.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NotContextException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("User with id %d not found", id)));
    }

    @GetMapping("")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("")
    public User addUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException(String.format("User with id %d not found", id));
        }
        userRepository.delete(user);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new EntityNotFoundException(String.format("User with id %d not found", id));
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setBirthday(user.getBirthday());
        existingUser.setSchool(user.getSchool());
        existingUser.setMajor(user.getMajor());
        existingUser.setOrg(user.getOrg());

        userRepository.save(existingUser);
        return existingUser;
    }

    @GetMapping("/non-google-users")
    public Integer getNonGoogleUsers() {
        return userRepository.countByEmailNotContaining("google.com");
    }
}
