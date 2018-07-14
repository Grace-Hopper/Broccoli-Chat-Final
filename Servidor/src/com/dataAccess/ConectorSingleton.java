package com.dataAccess;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public final class ConectorSingleton {

    private static ConectorSingleton instance = new ConectorSingleton();
    private Session session;
    SessionFactory factory;
    private ConectorSingleton() {}
    
    public static ConectorSingleton getInstance() {
        return instance;
    }
    public Session getSession() {

        if (session == null) {
             factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
        }
        if(!session.isOpen())
        	  session = factory.openSession();
        return session;

    }

}