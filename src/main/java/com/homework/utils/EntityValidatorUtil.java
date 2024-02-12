package com.homework.utils;

import com.homework.exceptions.ClientForCreateShouldNotContainIdException;
import com.homework.hibernate_entities.Client;
import com.homework.hibernate_entities.Planet;
import com.homework.hibernate_entities.Ticket;

import java.util.Objects;

public final class EntityValidatorUtil {
    private static final int MIN_CLIENT_NAME_LENGTH = 3;
    private static final int MAX_CLIENT_NAME_LENGTH = 200;
    private static final int MIN_PLANET_NAME_LENGTH = 1;
    private static final int MAX_PLANET_NAME_LENGTH = 500;
    private static final String PLANET_ID_REGEX = "^[A-Z0-9]+$";

    private EntityValidatorUtil() {}

    public static boolean validateClient(Client client) {
        return Objects.nonNull(client)
                && Objects.nonNull(client.getName())
                && checkStringLengthInclusively(client.getName(), MIN_CLIENT_NAME_LENGTH, MAX_CLIENT_NAME_LENGTH);
    }

    public static boolean validatePlanet(Planet planet) {
        return Objects.nonNull(planet)
                && Objects.nonNull(planet.getName())
                && checkStringLengthInclusively(planet.getName(), MIN_PLANET_NAME_LENGTH, MAX_PLANET_NAME_LENGTH)
                && validatePlanetId(planet.getId());
    }

    private static boolean checkStringLengthInclusively(String string, int min, int max) {
        return string.length() <= max && string.length() >= min;
    }
    public static boolean validatePlanetId(String id) {
        return id.matches(PLANET_ID_REGEX);
    }

    public static void throwIfClientIdForCreateIsNotNull(Client client) throws ClientForCreateShouldNotContainIdException {
        if(!Objects.isNull(client.getId())){
            throw new ClientForCreateShouldNotContainIdException();
        }
    }

    public static boolean checkForTicketCreate(Ticket ticket){
        return
                Objects.isNull(ticket.getId())
                && Objects.isNull(ticket.getCreatedAt())
                && checkRequiredTicketArgsNotNull(ticket);
    }

    public static boolean checkRequiredTicketArgsNotNull(Ticket ticket) {
        return Objects.nonNull(ticket.getClient())
                && Objects.nonNull(ticket.getToPlanet())
                && Objects.nonNull(ticket.getFromPlanet());
    }
    public static boolean checkIfClientAndPlanetIdsForTicketEntityNotNull(Client client, Planet toPlanet, Planet fromPlanet){
        return
                Objects.nonNull(client.getId())
                && Objects.nonNull(toPlanet.getId())
                && Objects.nonNull(fromPlanet.getId());
    }
}
