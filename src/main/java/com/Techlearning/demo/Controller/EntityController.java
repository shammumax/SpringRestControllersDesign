package com.Techlearning.demo.Controller;

import com.Techlearning.demo.JournalApp.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class EntityController {

    HashMap<Integer,Entity> map = new HashMap<>();
    @GetMapping("/allJournals")
    public Entity[] allJournals() {
        return map.values().toArray(new Entity[map.size()]);
    }
    @PostMapping("/add/{id}")
    public boolean insert(@PathVariable int id, @RequestBody Entity entity) {
        map.put(id, entity);
        return true;
    }
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable int id) {
       map.remove(id);
       return true;
    }
    @PutMapping("/update/{id}")
    public boolean update(@PathVariable int id, @RequestBody Entity entity) {
      map.put(id, entity);
      return true;
    }
}
