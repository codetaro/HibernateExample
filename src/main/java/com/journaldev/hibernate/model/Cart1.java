package com.journaldev.hibernate.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CART")
public class Cart1 {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cart_total")
    private double total;

    @ManyToMany(targetEntity = Item1.class, cascade = {CascadeType.ALL})
    @JoinTable(name = "CART_ITEMS",
            joinColumns = {@JoinColumn(name = "cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private Set<Item1> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Set<Item1> getItems() {
        return items;
    }

    public void setItems(Set<Item1> items) {
        this.items = items;
    }
}
