package com.homework.crud_services;
import com.homework.exceptions.IllegalfieldException;
import com.homework.hibernate_entities.Planet;
import com.homework.utils.DbConfigUtil;
import com.homework.utils.FlywayUtil;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;

class PlanetCrudServiceTest {

    private PlanetCrudService planetCrudService = new PlanetCrudService();
    @BeforeAll
    static void setUp(){
        FlywayUtil.startFlyway(DbConfigUtil.PropertyType.TEST_MODE);
    }
    @ParameterizedTest
    @MethodSource(value = "supplyPlanets")
    void testCreateAndRead(Planet planet) throws IllegalfieldException {
        String id = planetCrudService.create(planet);
        Planet returnFromDbPlanet = planetCrudService.readWithoutTickets(id);
        Assertions.assertEquals(planet, returnFromDbPlanet);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PLUTO", "URANUS", "SATURN", "JUPITER"})
    void testDeleteAndRead(String id) {
        planetCrudService.delete(id);
        Assertions.assertNull(planetCrudService.readWithoutTickets(id));
    }

    @ParameterizedTest
    @MethodSource(value = "supplyPlanets")
    void testCreateUpdateAndRead(Planet planet) throws IllegalfieldException {
        String id = planetCrudService.create(planet);
        planetCrudService.update(new Planet(id, planet.getName().toUpperCase()));
        Planet readPlanet = planetCrudService.readWithoutTickets(id);
        Assertions.assertEquals(planet.getName().toUpperCase(), readPlanet.getName());
    }
    static Stream<Arguments> supplyPlanets(){
        return Stream.of(
                Arguments.of(new Planet("PLUTO", "Pluto")),
                Arguments.of(new Planet("URANUS", "Uranus")),
                Arguments.of(new Planet("SATURN", "Saturn")),
                Arguments.of(new Planet("JUPITER", "Jupiter"))
        );
    }
}