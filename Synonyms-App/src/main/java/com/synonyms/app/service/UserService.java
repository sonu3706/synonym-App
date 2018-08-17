package com.synonyms.app.service;

import com.synonyms.app.model.User;

public interface UserService {

    public User loginUser(String userId, String password);
}
