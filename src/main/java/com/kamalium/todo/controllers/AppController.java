package com.kamalium.todo.controllers;

import com.kamalium.todo.viewmodel.ViewModel;
import com.kamalium.todo.entity.Item;
import com.kamalium.todo.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class AppController {
    @Autowired
    private Repository repository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Item> todoList = repository.findAll();
        model.addAttribute("items", new ViewModel(todoList));
        model.addAttribute("newitem", new Item());
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Item requestItem) {
        Item item = new Item(requestItem.getItem_value());
        item.setCreated_date(new Timestamp(System.currentTimeMillis()));
        repository.save(item);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ViewModel requestItems) {
        for (Item requestItem : requestItems.getTodoList()) {
            Optional<Item> dbItem = repository.findById(requestItem.getId());
            Item item = new Item(requestItem.getId(), requestItem.getItem_value());
            item.setId(requestItem.getId());
            item.setCreated_date(dbItem.get().getCreated_date());
            if (!requestItem.getItem_value().equals(dbItem.get().getItem_value())) {
                item.setUpdated_date(new Timestamp(System.currentTimeMillis()));
            }
            repository.save(item);
        }
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute ViewModel requestItems) {
        for (Item requestItem : requestItems.getTodoList()) {
            if (requestItem.getDelete_item() == true) {
                Item item = new Item(requestItem.getId());
                repository.delete(item);
            }
        }
        return "redirect:/";
    }
}