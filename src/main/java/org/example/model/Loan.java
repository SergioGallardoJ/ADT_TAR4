package org.example.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Date checkout_date;

    private Date due_date; //Prevision para devolver

    private Date returned_date; // Cuando se devolvió

    //CONSTRUCTORS

    public Loan(User user, Item item, Date checkout_date, Date due_date, Date returned_date) {
        this.user = user;
        this.item = item;
        this.checkout_date = checkout_date;
        this.due_date = due_date;
        this.returned_date = returned_date;
    }

    public Loan() {
    }

    //GETTER Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(Date checkout_date) {
        this.checkout_date = checkout_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getReturned_date() {
        return returned_date;
    }

    public void setReturned_date(Date returned_date) {
        this.returned_date = returned_date;
    }

    //TO STRING

    @Override
    public String toString() {
        return "Loan{" +
                ", user=" + user +
                ", item=" + item +
                ", checkout_date=" + checkout_date +
                ", due_date=" + due_date +
                ", returned_date=" + returned_date +
                '}';
    }
}
