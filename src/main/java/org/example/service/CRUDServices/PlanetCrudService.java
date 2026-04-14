package org.example.CRUDServices;

import org.example.DAO.PlanetDAOServiceImpl;
import org.example.DAO.PlanetDaoService;
import org.example.entity.Planet;

import java.util.List;

public class PlanetCrudService implements PlanetDaoService {
    PlanetDaoService planetDao;

    public PlanetCrudService(){
        planetDao = new PlanetDAOServiceImpl();
    }

    @Override
    public boolean createPlanet(String planetID, String planetName) {
        if(!(planetID.matches("^[A-Z]+$") && (planetName.length() >= 1 && planetName.length() <= 500))){
            throw new IllegalArgumentException("Введено некоректне ім'я або ID!");
        }
        List<Planet> allPlanets = planetDao.getAllPlanets();
        for (int i = 0; i < allPlanets.size(); i++) {
            if(allPlanets.get(i).getId().equals(planetID)) throw new IllegalArgumentException("Такий ID уже існує!");
        }
        return planetDao.createPlanet(planetID, planetName);
    }

    @Override
    public boolean deletePlanet(String planetID) {
        List<Planet> allPlanets = planetDao.getAllPlanets();
        for (int i = 0; i < allPlanets.size(); i++) {
            if(allPlanets.get(i).getId().equals(planetID)) return planetDao.deletePlanet(planetID);
        }
        throw new IllegalArgumentException("Такого ID не існує!");
    }

    @Override
    public List<Planet> getAllPlanets() {
        return planetDao.getAllPlanets();
    }

    @Override
    public Planet getPlanet(String planetID) {
        List<Planet> allPlanets = planetDao.getAllPlanets();
        for (int i = 0; i < allPlanets.size(); i++) {
            if(allPlanets.get(i).getId().equals(planetID)) return planetDao.getPlanet(planetID);
        }
        throw new IllegalArgumentException("Такого ID не існує!");
    }

    @Override
    public boolean updatePlanet(String planetID, String changedName) {
        List<Planet> allPlanets = planetDao.getAllPlanets();
        for (int i = 0; i < allPlanets.size(); i++) {
            if(allPlanets.get(i).getId().equals(planetID)) return planetDao.updatePlanet(planetID, changedName);
        }
        throw new IllegalArgumentException("Такого ID не існує!");
    }
}
