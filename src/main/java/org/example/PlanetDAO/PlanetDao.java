package org.example.PlanetDAO;

import org.example.entity.Planet;

import java.util.List;

public interface PlanetDao {

    boolean createPlanet(String planetID, String planetName);
    Planet getPlanet(String planetID);
    List<Planet> getAllPlanets();
    boolean updatePlanet(String planetID, String changedName);
    boolean deletePlanet(String planetID);
}
