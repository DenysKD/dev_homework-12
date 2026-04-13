package org.example.ClientDAO;

import org.example.entity.Client;

import java.util.List;

public interface ClientDao {

    boolean createClient(String name);
    Client getClient(long clientID);
    List<Client> getAllClients();
    boolean updateClient(long clientID, String clientName);
    boolean deleteClient(long clientID);

}
