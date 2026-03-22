package com.example.demo.service;

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
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Logic: Check if user exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists!");
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User getUserById(Long id) {
        Optional<User> userOptional=userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }else{
            throw new RuntimeException();
        }
    }
    public User userUpdate(Long id, UserUpdateRequest request){
        Optional<User> userOptional=userRepository.findById(id);
        User user = userOptional.orElseThrow(()->
                new UserNotFoundException("用户不存在，ID："+id));
        // 3. 更新字段 (实际业务中可能需要判断字段是否为 null 再决定是否覆盖)
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        // 4. 保存 (JPA 会自动识别这是更新操作，因为 ID 存在)
        return userRepository.save(user);
    }

}