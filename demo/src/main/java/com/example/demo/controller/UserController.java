package com.example.demo.controller;

import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST: http://localhost:8080/api/users/register
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // GET: http://localhost:8080/api/users
    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    // DELETE: http://localhost:8080/api/users/1
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        try {
            User user=userService.getUserById(id);
            return ResponseEntity.ok(user);
        }catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        try{
            User user=userService.userUpdate(id,request);
            return ResponseEntity.ok(user);
        }catch(UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"系统内部错误\"}");
        }
    }

}