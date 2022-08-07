package com.kamalium.todo.controllers;

import com.kamalium.todo.entity.Item;
import com.kamalium.todo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;
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

    @PostMapping("/add")
    public @ResponseBody
    RestResponse addItem(@RequestBody Map<String, String> params) {
        String item_value = params.containsKey("item_value") ? params.get("item_value") : "";
        if (item_value.length() == 0) {
            return new RestResponse("NOT_ADDED", null);
        }
        Item item = new Item(item_value);
        item.setCreated_date(new Timestamp(System.currentTimeMillis()));
        Item saved = repository.save(item);
        return new RestResponse("ADDED", saved);
    }

    @PostMapping("/update")
    public @ResponseBody
    RestResponse updateItem(@RequestBody Map<String, String> params) {
        long id = params.containsKey("id") ? Long.valueOf(params.get("id")) : -1;
        String item_value = params.containsKey("item_value") ? params.get("item_value") : "";
        Optional<Item> dbItem = repository.findById(id);
        if (dbItem.isEmpty() || (item_value.length() == 0)) {
            return new RestResponse("NOT_UPDATED", null);
        }
        Item item = new Item(item_value);
        item.setId(id);
        item.setCreated_date(dbItem.get().getCreated_date());
        if (!item_value.equals(dbItem.get().getItem_value())) {
            item.setUpdated_date(new Timestamp(System.currentTimeMillis()));
        }
        Item saved = repository.save(item);
        return new RestResponse("UPDATED", item);
    }

    @DeleteMapping("/delete")
    public @ResponseBody
    RestResponse deleteItem(@RequestParam long id) {
        Item item = new Item(id);
        item.setId(id);
        repository.delete(item);
        return new RestResponse("DELETED", item);
    }


}
