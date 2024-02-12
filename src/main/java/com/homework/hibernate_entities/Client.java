package com.homework.hibernate_entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@NamedEntityGraph(
        name = "fetch-client-with-tickets-graph",
        attributeNodes = {
            @NamedAttributeNode("id"),
            @NamedAttributeNode("name"),
            @NamedAttributeNode("tickets")
    }
)
@Entity
@Table(name = "client")
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Data
public class Client {
    public Client(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
