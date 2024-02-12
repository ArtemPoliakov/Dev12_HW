package com.homework.crud_services;

import com.homework.dao.TicketDao;
import com.homework.exceptions.InvalidTicketException;
import com.homework.hibernate_entities.Ticket;
import com.homework.utils.EntityValidatorUtil;
import org.hibernate.SessionFactory;

public class TicketCrudService {
    private final TicketDao ticketDao;
    public TicketCrudService(SessionFactory sessionFactory){
        ticketDao = new TicketDao(sessionFactory);
    }
    public Long create(Ticket ticket) throws InvalidTicketException{
        if(EntityValidatorUtil.checkForTicketCreate(ticket)){
            return ticketDao.create(ticket);
        } else {
            throw new InvalidTicketException();
        }
    }
    public void update(Ticket ticket) throws InvalidTicketException {
        if(EntityValidatorUtil.checkRequiredTicketArgsNotNull(ticket)){
            ticketDao.update(ticket);
        }else {
            throw new InvalidTicketException();
        }
    }
    public Ticket read(Long id){
        return ticketDao.read(id);
    }
    public void delete(Long id){
        ticketDao.delete(id);
    }
}
