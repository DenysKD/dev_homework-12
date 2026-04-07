package org.example.ClientDAO;

import org.example.entity.Client;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDAO {

    public boolean createClient(String name){

        Transaction transaction = null;
        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()){

            transaction = session.beginTransaction();

            Client client = new Client();
            client.setName(name);
            session.persist(client);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public Client getClient(long clientID){

        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()){

            Client client = session.find(Client.class, clientID);
            return client;
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Щось пішло не так!");
        }
    }

    public List<Client> getAllClients(){

        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()) {

            List<Client> allClients = session.createQuery("from Client", Client.class).list();

            return allClients;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Щось пішло не так!");
        }
    }

    public boolean updateClient(long clientID, String clientName){

        Transaction transaction = null;
        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()){

            Client mergeClient = this.getClient(clientID);
            if(mergeClient == null) return false;

            transaction = session.beginTransaction();

            mergeClient.setName(clientName);
            session.merge(mergeClient);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteClient(long clientID){

        Transaction transaction = null;
        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()){
            Client deletedClient = this.getClient(clientID);
            if(deletedClient == null) return false;

            transaction = session.beginTransaction();
            session.remove(deletedClient);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
