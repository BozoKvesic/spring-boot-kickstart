package com.spring.boot.kickstart.bicycleproject.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bike {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String brand;
    private int price;

    private String color;

    public Bike(final int id, final String name, final String brand, final int price, final String color) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public Bike(final String name, final String brand, final int price, final String color) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.color = color;
    }

    public Bike(final String name, final String brand, final int price, final String color, final List<Bill> bills) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.color = color;
        this.bills = bills;
    }

    public Bike() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bike{" + "id=" + id + ", name='" + name + '\'' + ", brand='" + brand + '\'' + ", price=" + price + ", color='" + color + '\'' + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Bike bike = (Bike) o;
        return this.id == bike.id && this.price == bike.price && this.name.equals(bike.name) && this.brand.equals(bike.brand) && this.color.equals(bike.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.brand, this.price, this.color);
    }

    @ManyToMany(mappedBy = "bikes")
    private List<Bill> bills = new ArrayList<>();

    @JsonIgnore
    public List<Bill> getBills() {
        return this.bills;
    }

    public void setBills(final List<Bill> bills) {
        this.bills = bills;
    }

}
