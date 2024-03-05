package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private String description;


    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;

    @ManyToMany
    @JoinTable(name = "Category_id", joinColumns = @JoinColumn(name = "item_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
    private List<Category> category = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Loan> loans = new ArrayList<>();

    //CONSTRUCTORES
    public Item(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public Item() {
    }

    //GETTERS Y SETTERS

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public List<Category> getCategories() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
    //TO STRING

    @Override
    public String toString() {
        return "Item{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
