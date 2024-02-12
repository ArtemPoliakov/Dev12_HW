package com.homework.dao;

import com.homework.hibernate_entities.Ticket;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
public class TicketDao {
    private final SessionFactory sessionFactory;
    public TicketDao(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Long create(Ticket ticket){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        }
        return ticket.getId();
    }
    public void update(Ticket ticket){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(ticket);
            transaction.commit();
        }
    }
    public Ticket read(Long id){
        Ticket result;
        try(Session session = sessionFactory.openSession()){
            result = session.get(Ticket.class, id);
        }
        return result;
    }
    public void delete(Long id){
        try(Session session = sessionFactory.openSession()){
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Ticket> criteriaDelete = criteriaBuilder.createCriteriaDelete(Ticket.class);
            Root<Ticket> root = criteriaDelete.from(Ticket.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createMutationQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        }
    }
}
