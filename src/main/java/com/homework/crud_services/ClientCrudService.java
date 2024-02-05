package com.homework.crud_services;

import com.homework.utils.HibernateUtil;
import com.homework.utils.EntityValidatorUtil;
import com.homework.dao.ClientDao;
import com.homework.exceptions.ClientForCreateShouldNotContainIdException;
import com.homework.hibernate_entities.Client;


public class ClientCrudService {
    public static final long DEFAULT_ID_FOR_MISTAKE = -1L;
    private final ClientDao clientDao = new ClientDao(HibernateUtil.getInstance().getSessionFactory());

    public Long create(Client client) throws ClientForCreateShouldNotContainIdException {
        EntityValidatorUtil.throwIfClientIdForCreateIsNotNull(client);
        Long returnedId = DEFAULT_ID_FOR_MISTAKE;
        if (EntityValidatorUtil.validateClient(client)) {
            returnedId = clientDao.create(client);
        }
        return returnedId;
    }

    public void delete(Long id) {
        clientDao.delete(id);
    }

    public void update(Client client) {
        if (EntityValidatorUtil.validateClient(client)) {
            clientDao.update(client);
        }
    }

    public Client read(Long id) {
        return clientDao.read(id);
    }
}
