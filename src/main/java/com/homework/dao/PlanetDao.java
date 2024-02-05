package com.homework.dao;

import com.homework.hibernate_entities.Client;
import com.homework.hibernate_entities.Planet;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

public class PlanetDao {
    private final SessionFactory sessionFactory;
    public PlanetDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void create(Planet planet){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        }
    }
    public void update(Planet planet){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(planet);
            transaction.commit();
        }
    }
    public Planet read(String id){
        try(Session session = sessionFactory.openSession()){
            return session.get(Planet.class, id);
        }
    }
    public void delete(String id){
        try(Session session = sessionFactory.openSession()){
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Planet> criteriaDelete = criteriaBuilder.createCriteriaDelete(Planet.class);
            Root<Planet> root = criteriaDelete.from(Planet.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createMutationQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        }
    }
}
