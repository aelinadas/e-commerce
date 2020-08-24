/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import neu.edu.ecommerce.pojo.Consumer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author aelinadas
 */
public class ConsumerDAO {
    private static final Logger logger = Logger.getLogger(ConsumerDAO.class);
    
    public List<Consumer> getConsumers() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        List<Consumer> consumers = new ArrayList<Consumer>();
        try {
            connectionDAO.beginTransaction();
            Query q = connectionDAO.getSession().createQuery("from Consumer");
            consumers = q.list();
            connectionDAO.commit();
        } catch (HibernateException e) {
            logger.error(e);
            e.printStackTrace();
            connectionDAO.rollbackTransaction();
        } finally {
            connectionDAO.close();
        }
        return consumers;
    }
    public int addNewConsumer(String fname, String lname, int age, String phoneNumber, String email, String password, String street, String city, String state, String country, String zipCode){
        int result = 0;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try{
            Consumer consumer = new Consumer();
            consumer.setFname(fname);
            consumer.setLname(lname);
            consumer.setAge(age);
            consumer.setEmail(email);
            consumer.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            consumer.setPhoneNumber(phoneNumber);
            consumer.setStreet(street);
            consumer.setCity(city);
            consumer.setState(state);
            consumer.setCountry(country);
            consumer.setZipCode(zipCode);
            consumer.setStatus(Byte.MIN_VALUE);
            connectionDAO.beginTransaction();
            connectionDAO.getSession().save(consumer);
            connectionDAO.commit();
            result = 1;
            
        }catch (HibernateException e) {
            logger.error(e);
            e.printStackTrace();
            connectionDAO.rollbackTransaction();
        } finally {
            connectionDAO.close();
        }
        return result;
    }
    
    public int deactivateConsumers(int id) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            connectionDAO.beginTransaction();
            Query q = connectionDAO.getSession().createQuery("from Consumer where id= :id");
            q.setInteger("id", id);
            Consumer consumerToDeactivate = (Consumer) q.uniqueResult();
            connectionDAO.getSession().delete(consumerToDeactivate);
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
    
    public Consumer authenticateConsumer(String email, String password) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        Consumer consumer = null;
        Boolean valid = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("from Consumer where email = :id");
            query.setString("id", email);
            consumer = (Consumer) query.uniqueResult();
            connectionDAO.commit();
            if (consumer != null) {
                valid = checkPass(password, consumer.getPassword());
                if (!valid) {
                    consumer = null;
                }
            }
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return consumer;
    }
    
    public int updateConsumer(Consumer consumer, String fname, String lname, int age, String setPhoneNumber, 
        String street, String city, String state, String country, String zipCode){
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        int id = consumer.getId();
        try{
            connectionDAO.beginTransaction();
            Query q = connectionDAO.getSession().createQuery("from Consumer where id= :id");
            q.setInteger("id", id);
            consumer = (Consumer) q.uniqueResult();
            if (consumer != null) {
                consumer.setFname(fname);
                consumer.setLname(lname);
                consumer.setAge(age);
                consumer.setPhoneNumber(setPhoneNumber);
                consumer.setStreet(street);
                consumer.setCity(city);
                consumer.setState(state);
                consumer.setCountry(country);
                consumer.setZipCode(zipCode);
                connectionDAO.getSession().update(consumer);
                connectionDAO.commit();
                result = 1;
            }
        } catch(HibernateException e){
            logger.error(e);
            e.printStackTrace();
            connectionDAO.rollbackTransaction();
        } finally{
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
            query = connectionDAO.getSession().createQuery("from Consumer where email = :id");
            query.setString("id", email);
            ArrayList<Consumer> consumerList = (ArrayList<Consumer>) query.list();
            connectionDAO.commit();
            if (!consumerList.isEmpty()) {
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
    
}
