package com.Pandya.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")
@NamedQuery(name = "Inventory.getByName", query = "SELECT i from Inventory i where i.name = :name")
@NamedQuery(name = "Inventory.clearAll", query = "DELETE FROM Inventory ")
public class Inventory implements Comparable<Inventory>, Serializable{
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String sport;
    private Integer quantity;
    private Double pricePerUnit;

    private Date addModifyDate;

    @OneToMany(mappedBy = "inventory", fetch = FetchType.EAGER)
    private List<Store> storeList;

    @PrePersist
    void createdAt(){
        this.addModifyDate = new Date();
    }

    @PreUpdate
    void updatedAt(){
        this.addModifyDate = new Date();
    }

    public Inventory(String name, String sport,Integer quantity, Double pricePerUnit){
        this.name = name;
        this.sport = sport;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public int compareTo(Inventory o){
        return addModifyDate.compareTo(o.addModifyDate);
    }
}
