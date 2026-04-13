package org.example.CRUDServices;

import org.example.ClientDAO.ClientDAOService;
import org.example.ClientDAO.ClientDao;
import org.example.entity.Client;

import java.util.List;

public class ClientCrudService implements ClientDao {
    ClientDAOService clientDao;

    public ClientCrudService(){ clientDao = new ClientDAOService(); }

    @Override
    public boolean createClient(String name) {
        if(name == null || name.length() < 3 || name.length() > 200) throw new IllegalArgumentException("Введено не коректне ім'я!");

        return clientDao.createClient(name);
    }

    @Override
    public boolean deleteClient(long clientID) {
        if(clientID < 0) throw new IllegalArgumentException("ID слієнта не може бути від'ємним!");

        List<Client> allClients = clientDao.getAllClients();
        for (int i = 0; i < allClients.size(); i++) {
            if(allClients.get(i).getId().equals(clientID)) return clientDao.deleteClient(clientID);
        }
        throw new IllegalArgumentException("Такого ID не уснує!");
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    @Override
    public Client getClient(long clientID) {
        if(clientID < 0) throw new IllegalArgumentException("ID слієнта не може бути від'ємним!");

        List<Client> allClients = clientDao.getAllClients();
        for (int i = 0; i < allClients.size(); i++) {
            if(allClients.get(i).getId().equals(clientID)) return clientDao.getClient(clientID);
        }
        throw new IllegalArgumentException("Такого ID не уснує!");
    }

    @Override
    public boolean updateClient(long clientID, String clientName) {
        if(clientID < 0) throw new IllegalArgumentException("ID слієнта не може бути від'ємним!");
        if(clientName == null || clientName.length() < 3 || clientName.length() > 200) throw new IllegalArgumentException("Введено не коректне ім'я!");

        List<Client> allClients = clientDao.getAllClients();
        for (int i = 0; i < allClients.size(); i++) {
            if(allClients.get(i).getId().equals(clientID)) return clientDao.updateClient(clientID, clientName);
        }
        throw new IllegalArgumentException("Такого ID не уснує!");
    }
}
