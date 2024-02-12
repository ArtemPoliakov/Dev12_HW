package com.homework.utils;

import com.homework.hibernate_entities.Client;
import com.homework.hibernate_entities.Planet;
import com.homework.hibernate_entities.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    private static final HibernateUtil INSTANCE;
    private final SessionFactory sessionFactory;
    static {
        INSTANCE = new HibernateUtil();
    }
    private HibernateUtil(){
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }
    public static HibernateUtil getInstance(){
        return INSTANCE;
    }
    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    public void close(){
        sessionFactory.close();
    }
}
