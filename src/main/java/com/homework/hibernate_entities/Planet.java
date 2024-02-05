package com.homework.hibernate_entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "planet")
@Data
@AllArgsConstructor
public class Planet {
    public Planet(String id){
        this.id = id;
    }
    public Planet() {}
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name", length = 500)
    private String name;
}
