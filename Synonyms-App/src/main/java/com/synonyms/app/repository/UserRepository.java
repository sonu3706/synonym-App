package com.synonyms.app.repository;

import com.synonyms.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    User findByUserIdAndUserPassword(String userId, String userPassword);

}
