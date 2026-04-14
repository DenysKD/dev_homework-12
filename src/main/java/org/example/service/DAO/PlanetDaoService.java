package org.example.DAO;

import org.example.entity.Planet;

import java.util.List;

public interface PlanetDaoService {

    boolean createPlanet(String planetID, String planetName);
    Planet getPlanet(String planetID);
    List<Planet> getAllPlanets();
    boolean updatePlanet(String planetID, String changedName);
    boolean deletePlanet(String planetID);
}
