package com.synonyms.app.service;

import com.synonyms.app.model.User;
import com.synonyms.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){this.userRepository=userRepository;}


    @Override
    public User loginUser(String userId, String password) {

        User user = userRepository.findByUserIdAndUserPassword(userId,password);

        return user;
    }



}
