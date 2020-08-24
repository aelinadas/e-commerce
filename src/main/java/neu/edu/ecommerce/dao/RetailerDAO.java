/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.dao;

import java.util.ArrayList;
import neu.edu.ecommerce.pojo.Retailer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author aelinadas
 */
public class RetailerDAO {
    private static final Logger logger = Logger.getLogger(RetailerDAO.class);

    public ArrayList<Retailer> getRetailers() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<Retailer> retailers = new ArrayList<Retailer>();
        try {
            connectionDAO.beginTransaction();
            Query q = connectionDAO.getSession().createQuery("from Retailer where status= -128");
            retailers = (ArrayList<Retailer>) q.list();
            connectionDAO.commit();
        } catch (HibernateException e) {
            logger.error(e);
            e.printStackTrace();
            connectionDAO.rollbackTransaction();
        } finally {
            connectionDAO.close();
        }
        return retailers;
    }

    public int addNewRetailer(String name, String primaryContactName, String email, String password, String phoneNumber, String street, String city, String state, String country, String zipCode) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            Retailer retailer = new Retailer();
            retailer.setName(name);
            retailer.setPrimaryContactName(primaryContactName);
            retailer.setEmail(email);
            retailer.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            retailer.setPhoneNumber(phoneNumber);
            retailer.setStreet(street);
            retailer.setCity(city);
            retailer.setState(state);
            retailer.setCountry(country);
            retailer.setZipCode(zipCode);
            retailer.setStatus(Byte.MIN_VALUE);
            connectionDAO.beginTransaction();
            connectionDAO.getSession().save(retailer);
            connectionDAO.commit();
            result = 1;

        } catch (HibernateException e) {
            logger.error(e);
            e.printStackTrace();
            connectionDAO.rollbackTransaction();
        } finally {
            connectionDAO.close();
        }
        return result;
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        boolean valid = false;
        if (BCrypt.checkpw(plainPassword, hashedPassword)) {
            valid = true;
        }
        return valid;
    }

    public Retailer authenticateRetailer(String email, String password) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        Retailer retailer = null;
        Boolean valid = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("from Retailer where email = :id");
            query.setString("id", email);
            retailer = (Retailer) query.uniqueResult();
            connectionDAO.commit();
            if (retailer != null) {
                valid = checkPass(password, retailer.getPassword());
                if (!valid) {
                    retailer = null;
                }
            }
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return retailer;
    }

    public int updateRetailer(Retailer retailer, String name, String username, String contact,
            String street, String city, String state, String country, String zipCode) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        int id = retailer.getId();
        try {
            connectionDAO.beginTransaction();
            Query q = connectionDAO.getSession().createQuery("from Retailer where id= :id");
            q.setInteger("id", id);
            retailer = (Retailer) q.uniqueResult();
            if (retailer != null) {
                retailer.setName(name);
                retailer.setPrimaryContactName(username);
                retailer.setPhoneNumber(contact);
                retailer.setCity(city);
                retailer.setState(state);
                retailer.setCountry(country);
                retailer.setZipCode(zipCode);
                connectionDAO.getSession().update(retailer);
                connectionDAO.commit();
                result = 1;
            }
        } catch (HibernateException e) {
            logger.error(e);
            e.printStackTrace();
            connectionDAO.rollbackTransaction();
        } finally {
            connectionDAO.close();
        }
        return result;
    }

    public int activateRetailer(int id) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            Query query = null;
            connectionDAO.beginTransaction();
            query = connectionDAO.getSession().createQuery("from Retailer where id = :id");
            query.setInteger("id", id);
            Retailer retailer = (Retailer) query.uniqueResult();
            if (retailer.getStatus() == Byte.MIN_VALUE) {
                retailer.setStatus(Byte.MAX_VALUE);
            }
            connectionDAO.getSession().save(retailer);
            connectionDAO.commit();
            result = 1;

        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return result;
    }
    
    public int deactivateRetailers(int id) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            connectionDAO.beginTransaction();
            Query q = connectionDAO.getSession().createQuery("from Retailer where id= :id");
            q.setInteger("id", id);
            Retailer retailerToDelete = (Retailer) q.uniqueResult();
            connectionDAO.getSession().delete(retailerToDelete);
            connectionDAO.commit();
            result = 1;
        } catch (HibernateException e) {
            logger.error(e);
            e.printStackTrace();
            connectionDAO.rollbackTransaction();
        } finally {
            connectionDAO.close();
        }
        return result;
    }
    
    public boolean isUnique(String email) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        boolean exist = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("from Retailer where email = :id");
            query.setString("id", email);
            ArrayList<Retailer> retailerList = (ArrayList<Retailer>) query.list();
            connectionDAO.commit();
            if (!retailerList.isEmpty()) {
                exist = true;
            }
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return exist;
    }
    
   public Retailer getRetailer(int id) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        Retailer retailer = null;
        try {
            Query query = null;
            connectionDAO.beginTransaction();
            query = connectionDAO.getSession().createQuery("from Retailer where id = :id");
            query.setInteger("id", id);
            retailer = (Retailer) query.uniqueResult();
            connectionDAO.getSession().save(retailer);
            connectionDAO.commit();

        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return retailer;
    }
   
   public ArrayList<Retailer> getApprovedRetailerList() {
        ArrayList<Retailer> requestList = null;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Retailer WHERE status = 127");
            requestList = (ArrayList<Retailer>) query.list();
            connectionDAO.commit();
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return requestList;
    }
   
   public int deleteAccount(int id) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            Query query = null;
            connectionDAO.beginTransaction();
            query = connectionDAO.getSession().createQuery("From Retailer where id = :id");
            query.setInteger("id", id);
            Retailer retailer = (Retailer) query.uniqueResult();
            connectionDAO.getSession().delete(retailer);
            connectionDAO.commit();
            result = 1;
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
            logger.error(e);
        } finally {
            connectionDAO.close();
        }
        return result;
    }
   
   public boolean isNameUnique(String retailerName) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        boolean exist = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM Retailer WHERE name = :retailer");
            query.setString("retailer", retailerName);
            ArrayList<Retailer> rList = (ArrayList<Retailer>) query.list();
            connectionDAO.commit();
            if (!rList.isEmpty()) {
                exist = true;
            }
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return exist;
    }
   
}
