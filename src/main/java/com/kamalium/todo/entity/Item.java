package com.kamalium.todo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Item {

    @Id @GeneratedValue(strategy=GenerationType.AUTO) private Long id;
    private String item_value;
    private Timestamp created_date;
    private Timestamp updated_date;
    @Transient private boolean delete_item;

    public Item() {}

    public Item( long id, String item_value ) {
        this.item_value = item_value;
    }

    public Item(String item_value ) {
        this.item_value = item_value;
    }

    public Item( long id ){
        this.id=id;
    }


    @Override
    public String toString() {
        return String.format(
                "Item[id=%d, item_value='%s']",
                id, item_value);
    }

    public boolean getDelete_item() {
        return this.delete_item;
    }
    public void setDelete_item(boolean delete_item) {
        this.delete_item = delete_item;
        return;
    }


    public Timestamp getCreated_date() { return created_date; }
    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
        return;
    }

    public Timestamp getUpdated_date() { return updated_date; }
    public void setUpdated_date(Timestamp updated_date) {
        this.updated_date = updated_date;
        return;
    }

    public String getItem_value() {
        return item_value;
    }

    public void setItem_value(String item_value) {
        this.item_value = item_value;
        return;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        return;
    }

}