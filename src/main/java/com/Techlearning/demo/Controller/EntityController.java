package com.Techlearning.demo.Controller;

import com.Techlearning.demo.JournalApp.JournalEntity;
import com.Techlearning.demo.JournalApp.JournalEntity;
import com.Techlearning.demo.JournalApp.UserEntity;
import com.Techlearning.demo.Repository.JournalRepository;
import com.Techlearning.demo.Service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.TemporalQueries;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class EntityController {
    @Autowired
    JournalService journalService;
    @Autowired
    UserEntity userEntity;
   // private ApplicationContext applicationContext;
    //Create entry with help of user...
    @PostMapping("/create/{username}")
    public boolean createUser(@RequestBody JournalEntity journalEntity, @PathVariable String username){
        if(journalService.checkUsername(username)){
            //existing user
            //just add journal entries data here to existing user
            //1)get user 2)update user journal entries
            journalService.update(journalEntity,username);
        }
        else{
            //new user

            userEntity.setUserName(username);
            userEntity.setPassWord("12345678");
            journalEntity.setDate(LocalDate.now());
            journalService.savedata(journalEntity,userEntity);
        }

     return true;
 }
    //Get entries when we pass user name ...
    @GetMapping("/get/{username}")
    public List<UserEntity> getUser(@PathVariable String username){
        return journalService.
                getJournals(username);
    }
    //delete journal entry based on id of journal ....
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable String id){
        journalService.cascadeDelete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
