package com.alame.lab4.service;

import com.alame.lab4.model.User;
import com.alame.lab4.model.DefaultPostResponse;
import com.alame.lab4.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public DefaultPostResponse register(User user){
        if(userRepository.findByUsername(user.getUsername())!=null){
            return new DefaultPostResponse(false, "that username already use");
        }
        userRepository.save(user);
        return new DefaultPostResponse(true, "");
    }
    @Override
    public DefaultPostResponse authorize(User user){
        User findUser = userRepository.findByUsername(user.getUsername());
        if(findUser==null){
            return new DefaultPostResponse(false, "no such user");
        }
        if(findUser.equals(user)){
            return new DefaultPostResponse(true, "");
        }
        return new DefaultPostResponse(false, "wrong password");
    }
}
