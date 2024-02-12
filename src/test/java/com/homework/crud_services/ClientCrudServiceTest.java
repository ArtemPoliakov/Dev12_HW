package com.homework.crud_services;

import com.homework.exceptions.ClientForCreateShouldNotContainIdException;
import com.homework.hibernate_entities.Client;
import com.homework.utils.DbConfigUtil;
import com.homework.utils.FlywayUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;


class ClientCrudServiceTest {
    private ClientCrudService clientCrudService = new ClientCrudService();
    @BeforeAll
    static void setUp(){
        FlywayUtil.startFlyway(DbConfigUtil.PropertyType.TEST_MODE);
    }
    @ParameterizedTest
    @MethodSource(value = "supplyClients")
    void testCreateAndRead(Client client) throws ClientForCreateShouldNotContainIdException {
        Long generatedId = clientCrudService.create(client);
        Client returnFromDbClient = clientCrudService.readWithoutTickets(generatedId);
        Assertions.assertEquals(client, returnFromDbClient);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4, 5})
    void testDeleteAndRead(Long id) {
        clientCrudService.delete(id);
        Assertions.assertNull(clientCrudService.readWithoutTickets(id));
    }

    @ParameterizedTest
    @MethodSource(value = "supplyClients")
    void testCreateUpdateAndRead(Client client) throws ClientForCreateShouldNotContainIdException {
        Long generatedId = clientCrudService.create(client);
        clientCrudService.update(new Client(generatedId, client.getName().toUpperCase()));
        Client readClient = clientCrudService.readWithoutTickets(generatedId);
        Assertions.assertEquals(client.getName().toUpperCase(), readClient.getName());
    }
    static Stream<Arguments> supplyClients(){
        return Stream.of(
                Arguments.of(new Client("Artem")),
                Arguments.of(new Client("Stepan")),
                Arguments.of(new Client("Andriy")),
                Arguments.of(new Client("Serhiy")),
                Arguments.of(new Client("Anton"))
        );
    }
}