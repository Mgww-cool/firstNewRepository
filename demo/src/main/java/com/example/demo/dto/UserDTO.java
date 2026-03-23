package com.example.demo.dto;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "username should not be blank")
    private String username;
    @Email
    private String email;
    @Size(min=5,max=12,message = "password size should be 5 to 12")
    private String password;
}
