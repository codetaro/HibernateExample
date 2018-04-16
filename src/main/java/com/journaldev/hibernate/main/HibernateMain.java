package com.journaldev.hibernate.main;

import com.journaldev.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;

public class HibernateMain {

    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setName("Pankaj");
        emp.setRole("CEO");
        emp.setInsertTime(new Date());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(emp);
        session.getTransaction().commit();
        System.out.println("Employee ID=" + emp.getId());

        HibernateUtil.getSessionFactory().close();
    }
}
