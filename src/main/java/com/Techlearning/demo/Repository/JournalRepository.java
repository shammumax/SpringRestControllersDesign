package com.Techlearning.demo.Repository;

import com.Techlearning.demo.JournalApp.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalEntity,String> {
}
