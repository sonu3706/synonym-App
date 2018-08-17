package com.synonyms.app.controller;

import com.synonyms.app.exceptions.UserDetailsNotFoundExceptions;
import com.synonyms.app.exceptions.UserNotFoundExceptions;
import com.synonyms.app.jwt.SecurityTokenGenrator;
import com.synonyms.app.model.User;
import com.synonyms.app.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Method to login user with userId and password, once done it returns jwt token to the user.

    @PostMapping
    public ResponseEntity loginUser(@RequestBody User user) {

        ResponseEntity responseEntity = null;

        try {

            String userId = user.getUserId();
            String password = user.getUserPassword();

            if (userId == null || password == null) {
                throw new UserDetailsNotFoundExceptions("UserId and Password cannot be empty");
            }

            User userDetails = userService.loginUser(userId, password);

            if (userDetails == null) {
                System.out.println(111);
                throw new UserNotFoundExceptions("User with given Id does not exists");
            }

            String fetchedPassword = userDetails.getUserPassword();
            if (!password.equals(fetchedPassword)) {
                System.out.println(2222);
                throw new UserNotFoundExceptions("User with given Id does not exists");

            }

            //Generating token

            SecurityTokenGenrator securityTokenGenrator = (User userData) -> {
                String jwtToken = "";
                jwtToken = Jwts.builder().setId(userData.getUserId()).setSubject(userData.getUserId()).setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
                Map<String, String> map1 = new HashMap<>();
                map1.put("token", jwtToken);
                map1.put("message", "User successfully logged in");
                return map1;
            };

            Map<String, String> map = securityTokenGenrator.generateToken(user);

            responseEntity = new ResponseEntity(map, HttpStatus.OK);


        } catch (UserDetailsNotFoundExceptions | UserNotFoundExceptions exception) {
            responseEntity = new ResponseEntity(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }


        return responseEntity;
    }
}
