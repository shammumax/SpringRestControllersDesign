package com.Techlearning.demo.JournalApp;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user_entries")
@Component
public class UserEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;

    private String password;
    @DBRef
    @Field("lst")
    private List<JournalEntity> userjournalentries = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public List<JournalEntity> getUserjournalentries() {
        return userjournalentries;
    }

    public void setUserjournalentries(List<JournalEntity> userjournalentries) {
        this.userjournalentries = userjournalentries;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }
}
