package com.journaldev.hibernate.main;

import com.journaldev.hibernate.model.Customer;
import com.journaldev.hibernate.model.Txn;
import com.journaldev.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class HibernateOneToOneMain {

    private static Txn buildDemoTransaction() {
        Txn txn = new Txn();
        txn.setDate(new Date());
        txn.setTotal(100);

        Customer cust = new Customer();
        cust.setAddress("Bangalore, India");
        cust.setEmail("pankaj@gmail.com");
        cust.setName("Pankaj Kumar");

        txn.setCustomer(cust);
        cust.setTxn(txn);
        return txn;
    }

    private static void printTransactionData(long id, SessionFactory sessionFactory) {
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            Txn txn = (Txn) session.get(Txn.class, id);
            tx.commit();

            System.out.println("Transaction Details=\n" + txn);
        } catch (Exception e) {
            System.out.println("Exception occured." + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Txn txn = buildDemoTransaction();

        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.getCurrentSession();
            System.out.println("Session created");

            tx = session.beginTransaction();
            session.save(txn);
            tx.commit();
            System.out.println("Transaction ID=" + txn.getId());

            printTransactionData(txn.getId(), sessionFactory);
        } catch (Exception e) {
            System.out.println("Exception occured." + e.getMessage());
            e.printStackTrace();
        } finally {
            if (!sessionFactory.isClosed()) {
                System.out.println("Closing SessionFactory");
                sessionFactory.close();
            }
        }
    }
}
