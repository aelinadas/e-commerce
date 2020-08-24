/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import neu.edu.ecommerce.pojo.Consumer;
import neu.edu.ecommerce.pojo.OrderedProduct;
import neu.edu.ecommerce.pojo.Product;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author aelinadas
 */
public class OrderedProductDAO {

    private static final Logger logger = Logger.getLogger(OrderedProductDAO.class);

    public double finalOrder(HashMap<Integer, Integer> orderMap, Consumer consumer) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<OrderedProduct> OrderedList = new ArrayList<OrderedProduct>();
        double total = 0;
        try {
            for (Map.Entry<Integer, Integer> mapElement : orderMap.entrySet()) {
                //System.out.println("Inside For" + mapElement.getKey() + ":" + mapElement.getValue());
                Integer key = mapElement.getKey();
                ProductDAO productDAO = new ProductDAO();
                connectionDAO.beginTransaction();

                Product p = productDAO.getProduct(key);
                OrderedProduct orderedProduct = new OrderedProduct();
                orderedProduct.setCategory(p.getCategory());
                orderedProduct.setConsumer(consumer);
                orderedProduct.setDescription(p.getDescription());
                orderedProduct.setPrice(p.getPrice());
                orderedProduct.setProductName(p.getProductName());
                orderedProduct.setQuantity(mapElement.getValue());
                orderedProduct.setRetailerName(p.getRetailerName());
                orderedProduct.setStoreProductId(p.getId());
                orderedProduct.setOrderDateTime(new Timestamp(System.currentTimeMillis()));

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss");
                String formattedDate = sdf.format(date);
                String splitdate = formattedDate.split(" ")[0].split("/")[0] + formattedDate.split(" ")[0].split("/")[1] + formattedDate.split(" ")[0].split("/")[2];
                String splittime = formattedDate.split(" ")[1].split(":")[0] + formattedDate.split(" ")[1].split(":")[1] + formattedDate.split(" ")[1].split(":")[2];
                orderedProduct.setOrderNumber(splitdate + consumer.getEmail().split("@")[0] + splittime);

                consumer.getOrderList().add(orderedProduct);
                connectionDAO.getSession().save(orderedProduct);
                connectionDAO.getSession().update(consumer);

                total += mapElement.getValue() * p.getPrice();

                p.setQuantity(p.getQuantity() - mapElement.getValue());
                connectionDAO.getSession().update(p);

                connectionDAO.commit();
            }

//            if (!OrderedList.isEmpty()) {
//                result = addToConsumer(OrderedList, consumer.getId());
//            }
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return total;
    }

    public ArrayList getOrder(int consumerId) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<OrderedProduct> orderList = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createSQLQuery("Select * from ecommercedb.orderedProducts where idConsumer = :id").addEntity(OrderedProduct.class);
            query.setInteger("id", consumerId);
            orderList = (ArrayList<OrderedProduct>) query.list();
            connectionDAO.commit();
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return orderList;
    }

    private int addToConsumer(ArrayList<OrderedProduct> OrderedList, int id) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            connectionDAO.beginTransaction();
            Query q = connectionDAO.getSession().createQuery("from Consumer where id= :id");
            q.setInteger("id", id);
            Consumer consumer = (Consumer) q.uniqueResult();
            for (OrderedProduct orderedProduct : OrderedList) {
                consumer.getOrderList().add(orderedProduct);
            }
            connectionDAO.getSession().saveOrUpdate(consumer);
            connectionDAO.commit();
            result = 1;
        } catch (HibernateException e) {
            logger.error(e);
            System.out.println(e.getMessage());
            connectionDAO.rollbackTransaction();
        } finally {
            connectionDAO.close();
        }
        return result;
    }
    
    public OrderedProduct getOrderById(int orderId) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        OrderedProduct orderedProduct = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("FROM OrderedProduct WHERE orderedProductId= :orderId");
            query.setInteger("orderId", orderId);
            orderedProduct = (OrderedProduct) query.uniqueResult();
            connectionDAO.commit();
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return orderedProduct;
    }
    
    public ArrayList<OrderedProduct> getAllOrder() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<OrderedProduct> orderList = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("From OrderedProduct");
            orderList = (ArrayList<OrderedProduct>) query.list();
            connectionDAO.commit();
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return orderList;
    }
    
}
