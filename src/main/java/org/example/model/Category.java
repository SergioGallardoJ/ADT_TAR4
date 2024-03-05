package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    //CONSTRUCTORES

    public Category(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    //SETTERS Y GETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    //TO STRING

    @Override
    public String toString() {
        return "Category{" +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
