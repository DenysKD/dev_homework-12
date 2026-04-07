package org.example.hibernate;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static final HibernateUtils INSTANCE = new HibernateUtils();
    private SessionFactory sessionFactory;

    private HibernateUtils(){

        this.sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .buildSessionFactory();
    }

    public static HibernateUtils getInstance(){
        return INSTANCE;
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public void closeSessionFuctory(){
        this.sessionFactory.close();
    }
}
