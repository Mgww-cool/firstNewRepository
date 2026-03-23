package com.example.demo.service;

import com.example.demo.dto.GetAllUserResponse;
import com.example.demo.dto.UserFindByIdRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    //register
    public User register(User user){
        //if user already exist

        //else
        return userRepository.save(user);
    }

    //getAll
    public List<User> getAll(){
        return userRepository.findAll();
    }

    //find by id
    public UserFindByIdRequest findById(Long id){
        //if Id is not exist
        Optional<User> userOptional=userRepository.findById(id);
        User user = userOptional.orElseThrow(
                ()->new UserNotFoundException("Not Found ID:"+id)
        );
        return new UserFindByIdRequest(user.getUsername(),user.getEmail());
    }

    //Update
    public void userUpdate(Long id,UserUpdateRequest userUpdateRequest){
        Optional<User> userOptional=userRepository.findById(id);
        User user =userOptional.orElseThrow(
                ()->new UserNotFoundException("Not Found ID:"+id)
        );
        userRepository.save(user);
    }

    //delete by id
    public void deleteUserById(Long id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }else{
            throw new UserNotFoundException("Not Found Id:"+id+" can not delete");
        }
    }
}