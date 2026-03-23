package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.common.ResultCode;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController{
    @Autowired
    UserService userService;

    //register
    @PostMapping()
    public Result<UserVO> register(@Valid @RequestBody UserDTO userDTO){
        UserVO userVO=userService.userRegister(userDTO);
        return new Result<UserVO>(ResultCode.SUCCESS,userVO);
    }

    //getAll
    @GetMapping("/list")
    public Result<List<UserVO>> getAllUser(){
        List<UserVO> voList=userService.getAllUser();
        return new Result<List<UserVO>>(ResultCode.SUCCESS,voList);
    }

    //find by id
    @GetMapping("/{id}")
    public Result<UserVO> findById(@PathVariable Long id){
        UserVO userVO=userService.findById(id);
        return new Result<>(ResultCode.SUCCESS,userVO);
    }

    //update
    @PutMapping("/{id}")
    public Result<UserVO> updateUser(@PathVariable Long id,@Valid @RequestBody UserDTO userDTO){
        UserVO userVO=userService.updateUser(id,userDTO);
        return new Result<>(ResultCode.SUCCESS,userVO);
    }

    //delete
    @DeleteMapping("/{id}")
    //为了统一，写成Void
    public Result<Void> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return Result.success(null);
    }
}