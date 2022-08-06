package com.kamalium.todo.repository;

import com.kamalium.todo.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Item, Long> {

}