package com.homework.dao;

import com.homework.hibernate_entities.Client;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.HashMap;
import java.util.Map;

public class ClientDao {
    private final SessionFactory sessionFactory;
    public ClientDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Long create(Client client){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
                session.persist(client);
            transaction.commit();
        }
        return client.getId();
    }
    public void update(Client client){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
                session.merge(client);
            transaction.commit();
        }
    }
    public Client readWithoutTickets(Long id){
        try(Session session = sessionFactory.openSession()){
                return session.get(Client.class, id);
        }
    }
    public void delete(Long id){
        try(Session session = sessionFactory.openSession()){
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Client> criteriaDelete = criteriaBuilder.createCriteriaDelete(Client.class);
            Root<Client> root = criteriaDelete.from(Client.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createMutationQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        }
    }

    public Client readWithTickets(Long id){
        try(Session session = sessionFactory.openSession()){
            EntityGraph entityGraph = session.getEntityGraph("fetch-client-with-tickets-graph");
            Map<String, Object> map = new HashMap<>();
            map.put("jakarta.persistence.loadgraph", entityGraph);
            return session.find(Client.class, id, map);
        }
    }
}
