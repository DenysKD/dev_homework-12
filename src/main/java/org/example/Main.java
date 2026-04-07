package org.example;

import org.example.ClientDAO.ClientDAO;
import org.example.DBConnection.DatabaseConnectoin;
import org.example.PlanetDAO.PlanetDAO;
import org.example.entity.Client;
import org.example.entity.Planet;

import java.util.List;

public class Main{

    static void main(String[] args) {
        DatabaseConnectoin dbc = DatabaseConnectoin.getInstance();

        PlanetDAO planetDAO = new PlanetDAO();
        ClientDAO clientDAO =new ClientDAO();

        //тестування Planet
        Planet somePlanet = null;
        List<Planet> allPlanets = null;
        if (planetDAO.createPlanet("SATURM", "saturm")){
            allPlanets = planetDAO.getAllPlanets();
            if(!allPlanets.isEmpty()) {
                somePlanet = planetDAO.getPlanet(allPlanets.get(allPlanets.size() - 1).getId());
                System.out.println(somePlanet.toString());
            }
        }
        System.out.println("--------------------------------");
        if(!allPlanets.isEmpty() && allPlanets != null) {
            allPlanets.forEach(p -> System.out.println(p.toString()));
        }
        if (planetDAO.updatePlanet("SATURM", "SATURM_SATURM")){
            allPlanets = planetDAO.getAllPlanets();
            if(!allPlanets.isEmpty()) {
                System.out.println("++++++++++++++++++++++++++");
                somePlanet = planetDAO.getPlanet(allPlanets.get(allPlanets.size() - 1).getId());
                System.out.println(somePlanet.toString());
                System.out.println("++++++++++++++++++++++++++");
            }
        }
        System.out.println("--------------------------------");
        if(planetDAO.deletePlanet("SATURM")){
            allPlanets = planetDAO.getAllPlanets();
            if(!allPlanets.isEmpty()) {
                allPlanets.forEach(p -> System.out.println(p.toString()));
            }
        }

        System.out.println("===========================================");

        //тестування Client
        Client someClient = null;
        List<Client> allClients = null;
        if(clientDAO.createClient("Josephine Jackson")){
            allClients = clientDAO.getAllClients();
            if(!allClients.isEmpty()) {
                someClient = clientDAO.getClient(allClients.get(allClients.size() - 1).getId());
                System.out.println(someClient.toString());
            }
        }
        System.out.println("--------------------------------");
        allClients.forEach(c -> System.out.println(c.toString()));
        if (clientDAO.updateClient(allClients.get(allClients.size() - 1).getId(), "JOSEPHINE JACKSON")){
            System.out.println("++++++++++++++++++++++++++");
            allClients = clientDAO.getAllClients();
            if(!allClients.isEmpty()) {
                someClient = clientDAO.getClient(allClients.get(allClients.size() - 1).getId());
                System.out.println(someClient.toString());
            }
            System.out.println("++++++++++++++++++++++++++");
        }
        System.out.println("--------------------------------");
        if (clientDAO.deleteClient(allClients.get(allClients.size() - 1).getId())){
            allClients = clientDAO.getAllClients();
            if(!allClients.isEmpty()) {
                allClients.forEach(c -> System.out.println(c.toString()));
            }
        }
    }
}
