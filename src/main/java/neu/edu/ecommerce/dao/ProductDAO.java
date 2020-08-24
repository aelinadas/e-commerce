/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.dao;

import java.util.ArrayList;
import java.util.HashSet;
import neu.edu.ecommerce.pojo.Product;
import neu.edu.ecommerce.pojo.Retailer;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author aelinadas
 */
public class ProductDAO {
    private static final Logger logger = Logger.getLogger(ProductDAO.class);
    public ArrayList<Product> getAllProducts(Retailer retailer) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<Product> productList = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createSQLQuery("Select * from ecommercedb.product where retailer_ID = :id").addEntity(Product.class);
            query.setInteger("id", retailer.getId());
            productList = (ArrayList<Product>) query.list();
            connectionDAO.commit();
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return productList;
    }

    public int addProduct(String productName, double price, String description, String category,
            int quantity, String retailerName, int numberOfProduct, int numbberOfProductPerCustomer, Retailer retailer) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        int result = 0;
        try {
            if (retailer != null) {
                connectionDAO.beginTransaction();
                Product product = new Product();
                product.setProductName(productName);
                product.setPrice(price);
                product.setDescription(description);
                product.setCategory(category);
                product.setQuantity(quantity);
                product.setRetailerName(retailerName);
                product.setNumberOfProduct(numberOfProduct);
                product.setNumbberOfProductPerCustomer(numbberOfProductPerCustomer);
                product.setRetailer(retailer);
                connectionDAO.getSession().save(product);
                retailer.getProductList().add(product);
                connectionDAO.getSession().update(retailer);
                connectionDAO.commit();
                result = 1;
            }
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return result;
    }

    public Product getProduct(int productId) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        Product product = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("from Product where id = :id");
            query.setInteger("id", productId);
            product = (Product) query.uniqueResult();
            connectionDAO.commit();
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return product;
    }

    public int updateProduct(int id, String productName, double price, String description, String category,
            int quantity, String retailerName, int numberOfProduct, int numbberOfProductPerCustomer, Retailer retailer) {
        int result = 0;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("from Product where id = :id");
            query.setInteger("id", id);
            Product product = (Product) query.uniqueResult();
            if (product == null) {
                return result;
            } else {
                product.setProductName(productName);
                product.setPrice(price);
                product.setDescription(description);
                product.setCategory(category);
                product.setQuantity(quantity);
                product.setRetailerName(retailerName);
                product.setNumberOfProduct(numberOfProduct);
                product.setNumbberOfProductPerCustomer(numbberOfProductPerCustomer);
                product.setRetailer(retailer);
                connectionDAO.getSession().update(product);
                connectionDAO.commit();
                result = 1;
            }
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return result;
    }

    public int deleteProduct(int id) {
        int result = 0;
        ConnectionDAO connectionDAO = new ConnectionDAO();
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("from Product where id = :id");
            query.setInteger("id", id);
            Product product = (Product) query.uniqueResult();
            if (product == null) {
                return result;
            } else {
                connectionDAO.getSession().delete(product);
                connectionDAO.commit();
                result = 1;
            }
        } catch (HibernateException e) {
            logger.error(e);
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return result;
    }

    public boolean isUnique(String productName) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        boolean exist = false;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("from Product where productName = :id");
            query.setString("id", productName);
            ArrayList<Product> productList = (ArrayList<Product>) query.list();
            connectionDAO.commit();
            if (!productList.isEmpty()) {
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

    public ArrayList<String> getCategory() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<String> productList = null;
        try {
            HashSet<String> categorySet = new HashSet<String>();
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createSQLQuery("Select category from ecommercedb.product where quantity>0");
            ArrayList results = (ArrayList) query.list();
            for (Object result : results) {
                categorySet.add(result.toString());
            }
            if (!categorySet.isEmpty()) {
                productList = new ArrayList<String>(categorySet);
            }
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return productList;
    }

    public ArrayList<Product> searchProducts(String name, String category) {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<Product> productList = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createSQLQuery("SELECT * FROM ecommercedb.product where name = :productName AND category = :category AND quantity>0").addEntity(Product.class);
            query.setString("productName", name);
            query.setString("category", category);
            productList = (ArrayList<Product>) query.list();
            connectionDAO.commit();
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return productList;    
    }
    
    public ArrayList<Product> getAllProducts() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        ArrayList<Product> productList = null;
        try {
            connectionDAO.beginTransaction();
            Query query = null;
            query = connectionDAO.getSession().createQuery("From Product");
            productList = (ArrayList<Product>) query.list();
            connectionDAO.commit();
        } catch (HibernateException e) {
            connectionDAO.rollbackTransaction();
            System.out.println(e.getMessage());
        } finally {
            connectionDAO.close();
        }
        return productList;
    }
}
