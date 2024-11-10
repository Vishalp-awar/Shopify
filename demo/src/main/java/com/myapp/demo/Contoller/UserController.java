package com.myapp.demo.Contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myapp.demo.Model.user;
import com.myapp.demo.Repository.UserRepository;

import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//@CrossOrigin(origins = "http://localhost:3002")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/alluser")
//    public List<user> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @PostMapping
//    public user createUser(@RequestBody user user) {
//        return userRepository.save(user);
//    }
//    @GetMapping("/test")
//    public String testEndpoint() {
//        return "Test successful!";
//    }
//
//}
//=
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // Allow CORS from the React app
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/alluser")
    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public user createUser(@RequestBody user user) {
        return userRepository.save(user);
    }
    
    @CrossOrigin(origins = "http://localhost:3000") // Allow CORS from the React app
    @PostMapping("/auht")
    public ResponseEntity<?> authuser(@RequestBody user user) {
        user check = userRepository.findByUsername(user.getUsername());

        // Check if the user exists and the password matches
        if (check != null && check.getPassword().equals(user.getPassword())) {
            // Return response as JSON
            return ResponseEntity.ok(Map.of("message", "Authentication successful"));
        } else {
            // Return response as JSON for invalid credentials
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
    }

}
