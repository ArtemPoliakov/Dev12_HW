package com.homework.crud_services;

import com.homework.exceptions.InvalidTicketException;
import com.homework.hibernate_entities.Client;
import com.homework.hibernate_entities.Planet;
import com.homework.hibernate_entities.Ticket;
import com.homework.utils.DbConfigUtil;
import com.homework.utils.FlywayUtil;
import com.homework.utils.HibernateUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TicketCrudServiceTest {
    TicketCrudService ticketCrudService = new TicketCrudService(HibernateUtil.getInstance().getSessionFactory());
    @BeforeAll
    static void setUp(){
        FlywayUtil.startFlyway(DbConfigUtil.PropertyType.TEST_MODE);
    }
    @Test
    void testTicket() throws InvalidTicketException {
        Client client = new Client(1L, "Michael Williams");
        Planet fromPlanet = new Planet("MARS", "Mars");
        Planet toPlanet = new Planet("NEPTUNE", "Neptune");
        Long returnedId = ticketCrudService.create(new Ticket(client, fromPlanet, toPlanet));
        System.out.println(returnedId);
    }
}