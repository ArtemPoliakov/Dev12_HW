package com.homework.hibernate_entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
@NamedEntityGraph(
        name = "planet-with-tickets-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "id"),
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "ticketsToThisPlanet"),
                @NamedAttributeNode(value = "ticketsFromThisPlanet")
        }
)

@Entity
@Table(name = "planet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planet {
    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Planet(String id){
        this.id = id;
    }
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name", length = 500)
    private String name;

    @OneToMany(mappedBy = "toPlanet", cascade = {PERSIST, MERGE, REMOVE, DETACH})
    private List<Ticket> ticketsToThisPlanet;
    @OneToMany(mappedBy = "fromPlanet", cascade = {PERSIST, MERGE, REMOVE, DETACH})
    private List<Ticket> ticketsFromThisPlanet;
}
