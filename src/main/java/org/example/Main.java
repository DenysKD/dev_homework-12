package org.example;

import org.example.CRUDServices.ClientCrudService;
import org.example.CRUDServices.PlanetCrudService;
import org.example.ClientDAO.ClientDAOService;
import org.example.DBConnection.DatabaseConnectoin;
import org.example.PlanetDAO.PlanetDAOService;
import org.example.entity.Client;
import org.example.entity.Planet;

import java.util.List;

public class Main{

    static void main(String[] args) {
        DatabaseConnectoin dbc = DatabaseConnectoin.getInstance();

        PlanetCrudService planetCrud = new PlanetCrudService();
        ClientCrudService clientCrud = new ClientCrudService();

        //тестування Planet
        Planet somePlanet = null;
        List<Planet> allPlanets = planetCrud.getAllPlanets();
        if (planetCrud.createPlanet("SATURM", "saturm")){
            if(!allPlanets.isEmpty()) {
                somePlanet = planetCrud.getPlanet(allPlanets.getLast().getId());
                System.out.println(somePlanet.toString());
            }
        }
        System.out.println("--------------------------------");
        if(!allPlanets.isEmpty() && allPlanets != null) {
            allPlanets.forEach(p -> System.out.println(p.toString()));
        }
        if (planetCrud.updatePlanet("SATURM", "SATURM_SATURM")){
            allPlanets = planetCrud.getAllPlanets();
            if(!allPlanets.isEmpty()) {
                System.out.println("++++++++++++++++++++++++++");
                somePlanet = planetCrud.getPlanet(allPlanets.get(allPlanets.size() - 1).getId());
                System.out.println(somePlanet.toString());
                System.out.println("++++++++++++++++++++++++++");
            }
        }
        System.out.println("--------------------------------");
        if(planetCrud.deletePlanet("SATURM")){
            allPlanets = planetCrud.getAllPlanets();
            if(!allPlanets.isEmpty()) {
                allPlanets.forEach(p -> System.out.println(p.toString()));
            }
        }

        System.out.println("===========================================");

        //тестування Client
        Client someClient = null;
        List<Client> allClients = clientCrud.getAllClients();
        if(clientCrud.createClient("Josephine Jackson")){
            if(!allClients.isEmpty()) {
                allClients = clientCrud.getAllClients();
                someClient = clientCrud.getClient(allClients.getLast().getId());
                System.out.println(someClient.toString());
            }
        }
        System.out.println("--------------------------------");
        allClients.forEach(c -> System.out.println(c.toString()));
        if (clientCrud.updateClient(allClients.get(allClients.size() - 1).getId(), "JOSEPHINE JACKSON")){
            System.out.println("++++++++++++++++++++++++++");
            allClients = clientCrud.getAllClients();
            if(!allClients.isEmpty()) {
                someClient = clientCrud.getClient(allClients.get(allClients.size() - 1).getId());
                System.out.println(someClient.toString());
            }
            System.out.println("++++++++++++++++++++++++++");
        }
        System.out.println("--------------------------------");
        if (clientCrud.deleteClient(allClients.get(allClients.size() - 1).getId())){
            allClients = clientCrud.getAllClients();
            if(!allClients.isEmpty()) {
                allClients.forEach(c -> System.out.println(c.toString()));
            }
        }
    }
}
