package com.synonyms.app.jwt;

import com.synonyms.app.model.User;

import java.util.Map;

@FunctionalInterface
public interface SecurityTokenGenrator {

    Map<String, String> generateToken(User user);
}
