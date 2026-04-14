package org.example.DAO;

import org.example.entity.Planet;
import org.hibernate.Transaction;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import java.util.List;

public class PlanetDAOServiceImpl implements PlanetDaoService {

    @Override
    public boolean createPlanet(String planetID, String planetName) {

        Transaction transaction = null;
        try (Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Planet planet = new Planet();
            planet.setId(planetID);
            planet.setName(planetName);
            session.persist(planet);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Planet getPlanet(String planetID){

        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()){

            Planet gettedPlanet = session.find(Planet.class, planetID);
            return gettedPlanet;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Щось пішло не так!");
        }
    }

    @Override
    public List<Planet> getAllPlanets(){

        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()){

            List<Planet> allPlanets = session.createQuery("from Planet", Planet.class).list();

            return allPlanets;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Щось пішло не так!");
        }
    }

    @Override
    public boolean updatePlanet(String planetID, String changedName){

        Transaction transaction = null;
        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()){

            Planet mergePlanet = this.getPlanet(planetID);
            if(mergePlanet == null) return false;

            transaction = session.beginTransaction();

            mergePlanet.setName(changedName);
            session.merge(mergePlanet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePlanet(String planetID){

        Transaction transaction = null;
        try(Session session = HibernateUtils.getInstance()
                .getSessionFactory().openSession()){
            Planet remowedPlanet = this.getPlanet(planetID);
            if(remowedPlanet == null) return false;

            transaction = session.beginTransaction();
            session.remove(remowedPlanet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
