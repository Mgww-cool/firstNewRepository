package com.example.demo.controller;

import com.example.demo.dto.UserFindByIdRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController{
    @Autowired
    UserService userService;

    //register
    @PostMapping()
   public  User register(@Valid @RequestBody User user){
       return userService.register(user);
   }

   //getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        List<User> userlist= userService.getAll();
        return ResponseEntity.ok(userlist);
    }

    //find by id
    @GetMapping("/{id}")
    public ResponseEntity<UserFindByIdRequest> findById(@PathVariable Long id) {
        UserFindByIdRequest userFindByIdRequest=userService.findById(id);
        return ResponseEntity.ok(userFindByIdRequest);
    }

    //Update
    @PutMapping("/update")
    public ResponseEntity<UserUpdateRequest>
    userUpdate(@Valid @RequestBody UserUpdateRequest userUpdateRequest,Long id){
        userService.userUpdate(id,userUpdateRequest);
        return ResponseEntity.ok(userUpdateRequest);
    }

    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById( @PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}