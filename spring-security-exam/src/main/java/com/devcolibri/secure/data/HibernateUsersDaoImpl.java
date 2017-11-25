package com.devcolibri.secure.data;

import com.devcolibri.secure.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class HibernateUsersDaoImpl implements UsersDao{
    public static void main(String[] args) {
        HibernateUsersDaoImpl g = new HibernateUsersDaoImpl();
        System.out.println(g.findUserByLogin("login").getLogin());
    }
    private static SessionFactory factory;
    @Override
    public User findUserByLogin(String login) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;

        tx = session.beginTransaction();
        List<Object[]> users = null;
        users = session.createSQLQuery("SELECT * FROM user WHERE login = " + login).list();


        tx.commit();

        session.close();

       return new User((Integer)users.get(0)[0],(String)users.get(0)[1],(String)users.get(0)[2]);
    }
}
