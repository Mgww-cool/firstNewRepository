package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data; // Optional: requires Lombok dependency to auto-generate getters/setters

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="username can not be empty")
    @Column(nullable = false, unique = true)
    private String username;

    @Email(message = "please enter correct email form")
    @Column(nullable = false)
    private String email;

    @Size(min=5,max=10,message = "password size should be 5 to 10")
    @Column(nullable = false)
    private String password;
}