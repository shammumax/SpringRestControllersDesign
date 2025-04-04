package com.Techlearning.demo.Repository;

import com.Techlearning.demo.JournalApp.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    List<UserEntity> findByusername(String userName);
}
