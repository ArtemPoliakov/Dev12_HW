package com.homework.hibernate_entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client")
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Client {
    public Client(String name){
        this.name = name;
    }
    public Client(Long id){
        this.id = id;
    }
    public Client(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private Long id;
    @Column(name = "name", nullable = false, length = 200)
    @Setter
    @Getter
    private String name;
}
