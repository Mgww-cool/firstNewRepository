package com.example.demo.service;

import com.example.demo.common.Result;
import com.example.demo.common.ResultCode;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.vo.UserVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    private String encryptPassword(String rawPassword) {
        // 简单示例：实际请使用 BCrypt.hashpw(rawPassword, BCrypt.gensalt())
        return "BCRYPT_" + rawPassword;
    }
    private UserVO convertToVO(User user){
        UserVO userVO =new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }
    private User convertToUser(UserDTO userDTO){
        User user=new User();
        BeanUtils.copyProperties(userDTO, user);
        //经过密码加密后拷贝
        user.setPassword(encryptPassword(userDTO.getPassword()));
        return user;
    }
    //register
    public UserVO userRegister(UserDTO userDTO) {
        //if name or email exist in database
        User existingUserByName = userRepository.findByUsername(userDTO.getUsername());
        if (existingUserByName != null) {
            // 抛出自定义业务异常，会被全局拦截器捕获并转为 Result.fail
            throw new BusinessException(ResultCode.FAIL, "用户名已被注册");
        }
        User existingUserByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (existingUserByEmail != null)
            throw new BusinessException(ResultCode.FAIL, "邮箱已被注册");
        //normal
        User newUser=convertToUser(userDTO);
        // save 方法执行后，newUser.getId() 会被数据库回填生成的主键 ID
        User savedUser = userRepository.save(newUser);

        //转uesr变成userVO，此时主键id一同转
        return convertToVO(newUser);
    }

    //getAllUsers
    public List<UserVO> getAllUser(){
        List<User> userList=userRepository.findAll();
        if(userList.isEmpty()){
            return Collections.emptyList();
        }
        return userList.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    //find by id
    public UserVO findById(Long id){
        User user=userRepository.findById(id).orElseThrow(
                ()->new BusinessException(ResultCode.USER_NOT_EXIST,null)
        );
        return convertToVO(user);
    }

    //Update
    @Transactional
    public UserVO updateUser(Long id,UserDTO userDTO){
        //id是否存在用户
        User existingUser=userRepository.findById(id).orElseThrow(
                ()->new BusinessException(ResultCode.USER_NOT_EXIST,null)
        );
        //看用户是否想改名字
        if(!existingUser.equals(userDTO.getUsername())){
            //只有想改才进入，里面的查询开销大
            if(userRepository.existsByUsername(userDTO.getUsername())){
                //数据库内有重名
                throw new BusinessException(ResultCode.FAIL,"名字与他人重复");
            }
        }
        User user=convertToUser(userDTO);
        user.setId(id);
        userRepository.save(user);
        return convertToVO(user);
    }

    //delete
    public void deleteById(Long id){
        //判断id存不存在
        User user=userRepository.findById(id).orElseThrow(
                ()->new BusinessException(ResultCode.USER_NOT_EXIST,null)
        );
        userRepository.deleteById(id);
    }

}
