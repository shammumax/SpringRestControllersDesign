package com.Techlearning.demo.Service;

import com.Techlearning.demo.JournalApp.JournalEntity;
import com.Techlearning.demo.JournalApp.UserEntity;
import com.Techlearning.demo.Repository.JournalRepository;
import com.Techlearning.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JournalService {
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserEntity userEntity;
    public void savedata(JournalEntity journalEntity, UserEntity userEntity) {

        journalRepository.save(journalEntity);
        List<JournalEntity> lst = new ArrayList<>();
        lst.add(journalEntity);
        userEntity.setUserjournalentries(lst);
        userRepository.save(userEntity);
    }
    public List<UserEntity> getJournals(String username) {
       List<UserEntity> userEntity= (List<UserEntity>) userRepository.findByusername(username);
        List<UserEntity> lst = new ArrayList<>();
        lst.addAll(userEntity);
        return lst;
    }
    public boolean checkUsername(String username) {
        List<UserEntity> lst= (List<UserEntity>)userRepository.findByusername(username);
        if (lst.isEmpty())
                return false;
        return true;
    }

    public void update(JournalEntity journalEntity, String username) {
        List<UserEntity> userEntity= (List<UserEntity>) userRepository.findByusername(username);
        journalEntity.setDate(LocalDate.now());
        journalRepository.save(journalEntity);
        userEntity.get(0).getUserjournalentries().add(journalEntity);
        userRepository.save(userEntity.get(0));
    }

    public void cascadeDelete(String id) {
        List<UserEntity> userEntity= (List<UserEntity>) userRepository.findAll();
        List<JournalEntity> lst ;
        boolean flag = false;
        JournalEntity tobedeletedjournal=null;
        for(UserEntity userEntity1: userEntity){ // all users here we traverse
            lst = userEntity1.getUserjournalentries(); // journals of each user
            for(JournalEntity journalEntity1: lst){
                if(journalEntity1.getId().equals(id)){
                    tobedeletedjournal=journalEntity1;
                    flag = true;
                    break;
                }
            }
            if(flag){
                //Below is complete one transaction partial transcations not allowed
                journalRepository.delete(tobedeletedjournal);
                //userRepository.deleteById(journalEntity1.getId());
                lst.remove(tobedeletedjournal);
                userEntity1.setUserjournalentries(lst);
                userRepository.save(userEntity1);
            }

        }
    }
}
