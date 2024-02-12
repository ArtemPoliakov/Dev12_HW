package com.homework.hibernate_entities;

import com.homework.exceptions.InvalidTicketException;
import com.homework.utils.EntityValidatorUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    public Ticket(Long id, Client client, Planet fromPlanet, Planet toPlanet) throws InvalidTicketException {
        this(client, fromPlanet, toPlanet);
        this.id = id;
    }
    public Ticket(Client client, Planet fromPlanet, Planet toPlanet) throws InvalidTicketException {
        if (EntityValidatorUtil.checkIfClientAndPlanetIdsForTicketEntityNotNull(client, fromPlanet, toPlanet)) {
            this.client = client;
            this.fromPlanet = fromPlanet;
            this.toPlanet = toPlanet;
        } else {
            throw new InvalidTicketException();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet fromPlanet;
    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet toPlanet;

    @Override
    public String toString(){
        return "id: " + this.id + " Client name= " + this.client.getName() + "("+ this.client.getId() + ")"
                + " fromPlanet: " + fromPlanet.getId() + " toPlanet: "  + this.toPlanet.getId()
                + " createdAt: " + this.createdAt;
    }
}
