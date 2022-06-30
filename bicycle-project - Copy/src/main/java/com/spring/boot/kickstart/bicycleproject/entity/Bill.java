package com.spring.boot.kickstart.bicycleproject.entity;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue
    @Column(name = "bill_id")
    private int id;

    @Column(name = "date_of_purchase")
    private LocalDate dateOfPurchase;

    @Column(name = "total_price")
    private int totalPrice;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
      name = "track",
      joinColumns = { @JoinColumn(name = "bill_id") },
      inverseJoinColumns = { @JoinColumn(name = "id") }
    )
    private List<Bike> bikes;
    public Bill() {
        this.bikes = new ArrayList<>();
    }

    public void addBike(Bike bike){
        this.bikes.add(bike);
        bike.getBills().add(this);
    }

    public Bill(final LocalDate dateOfPurchase, final int totalPrice, final List<Bike> bikes) {
        this.dateOfPurchase = dateOfPurchase;
        this.totalPrice = totalPrice;
        this.bikes = bikes;
    }

    public Bill(final int id, final LocalDate dateOfPurchase, final int totalPrice, final List<Bike> bikes) {
        this.id = id;
        this.dateOfPurchase = dateOfPurchase;
        this.totalPrice = totalPrice;
        this.bikes = bikes;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public LocalDate getDateOfPurchase() {
        return this.dateOfPurchase;
    }

    public void setDateOfPurchase(final LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(final int totalPrice) {
        this.totalPrice = totalPrice;
    }
    @JsonIgnore
    public List<Bike> getBikes() {
        return this.bikes;
    }

    public void setBikes(final List<Bike> bikes) {
        this.bikes = bikes;
    }

}
