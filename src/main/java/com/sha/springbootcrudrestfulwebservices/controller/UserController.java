package com.sha.springbootcrudrestfulwebservices.controller;

import com.sha.springbootcrudrestfulwebservices.entity.User;
import com.sha.springbootcrudrestfulwebservices.exception.ResourceNotFoundException;
import com.sha.springbootcrudrestfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User getUSerById(@PathVariable Long userId){
        return userRepository.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User not found by Id: "+userId));

    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);


    }

    @PutMapping("/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable long userId ){
        User existingUser = this.userRepository.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User not found with id: "+userId));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return this.userRepository.save(existingUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable long userId){
        User existingUser = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id :" + userId));
        userRepository.delete(existingUser);
        return ResponseEntity.ok().build();

    }
}
