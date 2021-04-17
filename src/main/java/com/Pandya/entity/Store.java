package com.Pandya.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
public class Store implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String location;

    @ManyToOne
    private Inventory inventory;

    public Store(String name, String location){
        this.name = name;
        this.location = location;
    }

}
