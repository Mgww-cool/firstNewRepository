package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class helloController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {

        String sql = "INSERT INTO user(name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getAge());

        return "插入成功";
    }
}