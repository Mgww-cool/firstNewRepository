package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.Data;

@Data
public class UserFindByIdRequest {
    private String username;
    private String email;
    public UserFindByIdRequest(String username,String email){
        this.email=email;
        this.username=username;
    }

    public UserFindByIdRequest(){}

}
