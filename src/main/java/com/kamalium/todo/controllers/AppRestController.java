package com.kamalium.todo.controllers;

import com.kamalium.todo.entity.Item;
import com.kamalium.todo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@RequestMapping(path = "/todo")
public class AppRestController {

    @Autowired
    private Repository repository;

    @GetMapping("/all")
    public @ResponseBody
    Iterable<Item> getAll() {
        return repository.findAll();
    }

    @GetMapping("/add")
    public @ResponseBody
    RestResponse addItem(@RequestParam String item_value) {
        Item item = new Item(item_value);
        item.setCreated_date(new Timestamp(System.currentTimeMillis()));
        Item saved = repository.save(item);
        return new RestResponse("Added", saved);
    }

    @GetMapping("/update")
    public @ResponseBody
    RestResponse updateItem(@RequestParam long id, @RequestParam String item_value) {
        Item item = new Item(item_value);
        Optional<Item> dbItem = repository.findById(id);
        item.setId(id);
        item.setCreated_date(dbItem.get().getCreated_date());
        if (!item_value.equals(dbItem.get().getItem_value())) {
            item.setUpdated_date(new Timestamp(System.currentTimeMillis()));
        }
        Item saved = repository.save(item);
        return new RestResponse("Updated", saved);
    }

    @GetMapping("/delete")
    public @ResponseBody
    RestResponse deleteItem(@RequestParam long id) {
        Item item = new Item(id);
        item.setId(id);
        repository.delete(item);
        return new RestResponse("Deleted",item);
    }


}
