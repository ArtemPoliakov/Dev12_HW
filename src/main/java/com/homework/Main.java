package com.homework;

import com.homework.crud_services.ClientCrudService;
import com.homework.crud_services.PlanetCrudService;
import com.homework.crud_services.TicketCrudService;
import com.homework.dao.ClientDao;
import com.homework.exceptions.ClientForCreateShouldNotContainIdException;
import com.homework.exceptions.IllegalfieldException;
import com.homework.exceptions.InvalidTicketException;
import com.homework.hibernate_entities.Client;
import com.homework.hibernate_entities.Planet;
import com.homework.hibernate_entities.Ticket;
import com.homework.utils.DbConfigUtil;
import com.homework.utils.FlywayUtil;
import com.homework.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final Long ID_FOR_TEST = 7L;
    public static void main(String[] args) throws InvalidTicketException, IllegalfieldException {
        PlanetCrudService planetCrudService = new PlanetCrudService();

        FlywayUtil.startFlyway(DbConfigUtil.PropertyType.REAL_MODE);

        Planet planet = planetCrudService.readWithTickets("EARTH");
        Ticket ticket = planet.getTicketsFromThisPlanet().get(0);
        System.out.println(ticket);
        ticket.setClient(new Client(10L, "SOMETHING"));

        planetCrudService.update(planet);

//        Planet planet = planetCrudService.readWithTickets("EARTH");
//        System.out.println("TO:");
//        for(Ticket ticket: planet.getTicketsToThisPlanet()){
//            System.out.println(ticket);
//        }
//        System.out.println("FROM:");
//        for(Ticket ticket: planet.getTicketsFromThisPlanet()){
//        System.out.println(ticket);
//        }










//        client.setName("Mr.Defaultius");
//        Planet fromPlanet = new Planet("MARS", "Mars");
//        Planet toPlanet = new Planet("MERCURY", "Mercury");
//        Ticket ticket = new Ticket(11L, client, fromPlanet, toPlanet);
//        client.getTickets().add(ticket);
//        clientCrudService.update(client);

//        try( Connection connection = DriverManager.getConnection(
//                DbConfigUtil.getDbUrl(DbConfigUtil.PropertyType.REAL_MODE),
//                DbConfigUtil.getDbUrl(DbConfigUtil.PropertyType.REAL_MODE),
//                DbConfigUtil.getDbPassword(DbConfigUtil.PropertyType.REAL_MODE)
//        ))
//        {
//            Statement statement = connection.createStatement();
//            statement.executeUpdate("CREATE TABLE ARTEM_TEST IF NOT EXISTS");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }





    //Not related to hw

    private static void testCriteriaQuery(Long id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
            Root<Client> root = criteriaQuery.from(Client.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
            Query<Client> query = session.createQuery(criteriaQuery);
            List<Client> resultList = query.getResultList();
            if(resultList.isEmpty()){
                System.out.println("nothing found");
            } else{
                for(Client client: resultList){
                    System.out.println(client);
                }
            }
        }
    }
    private static void testCriteriaUpdate(Long id){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaUpdate<Client> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Client.class);
            Root<Client> root = criteriaUpdate.from(Client.class);
            criteriaUpdate.set("name", "newNameSetByUpdate");
            criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
                session.createMutationQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        }
    }
    private static void testCriteriaDelete(Long id){
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()){
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Client> criteriaDelete = criteriaBuilder.createCriteriaDelete(Client.class);
            Root<Client> root = criteriaDelete.from(Client.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
                session.createMutationQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        }
    }
}