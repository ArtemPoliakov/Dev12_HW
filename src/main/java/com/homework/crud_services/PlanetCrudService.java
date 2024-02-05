package com.homework.crud_services;

import com.homework.exceptions.IllegalfieldException;
import com.homework.utils.EntityValidatorUtil;
import com.homework.utils.HibernateUtil;
import com.homework.dao.PlanetDao;
import com.homework.hibernate_entities.Planet;

public class PlanetCrudService {
    private static final PlanetDao planetDao = new PlanetDao(HibernateUtil.getInstance().getSessionFactory());

    public String create(Planet planet) throws IllegalfieldException {
        if (EntityValidatorUtil.validatePlanet(planet)) {
            planetDao.create(planet);
        } else {
            throw new IllegalfieldException();
        }
        return planet.getId();
    }

    public void delete(String id) {
        planetDao.delete(id);
    }

    public void update(Planet planet) throws IllegalfieldException {
        planetDao.update(planet);
    }

    public Planet read(String id) {
        return planetDao.read(id);
    }
}
