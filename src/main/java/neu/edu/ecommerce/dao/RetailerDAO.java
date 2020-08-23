/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import neu.edu.ecommerce.pojo.Retailer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author aelinadas
 */
public class RetailerDAO {
    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Session session = null;

    private Session getSession() {
        if (session == null || !session.isOpen()) {
            session = sf.openSession();
        }
        return session;
    }

    private void beginTransaction() {
        getSession().beginTransaction();
    }

    private void commit() {
        getSession().getTransaction().commit();;
    }

    private void close() {
        getSession().close();
    }

    private void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }
    
    public List<Retailer> getRetailers() {
        List<Retailer> retailers = new ArrayList<Retailer>();
        try {
            beginTransaction();
            Query q = getSession().createQuery("from Retailer");
            retailers = q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return retailers;
    }
    
    public int addNewRetailer(String name, String primaryContactName, String email, String password, String confirmpassword, String phoneNumber, String street, String city, String state, String country, String zipCode){
        int result = 0;
        try{
            Retailer retailer = new Retailer();
            retailer.setName(name);
            retailer.setPrimaryContactName(primaryContactName);
            retailer.setEmail(email);
            retailer.setPassword(password);
            retailer.setConfirmPassword(confirmpassword);
            retailer.setPhoneNumber(phoneNumber);
            retailer.setStreet(street);
            retailer.setCity(city);
            retailer.setState(state);
            retailer.setCountry(country);
            retailer.setZipCode(zipCode);
            retailer.setStatus(Byte.MIN_VALUE);
            beginTransaction();
            getSession().save(retailer);
            commit();
            result = 1;
            
        }catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }      
}
