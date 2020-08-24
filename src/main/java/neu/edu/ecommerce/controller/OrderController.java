/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import neu.edu.ecommerce.dao.OrderedProductDAO;
import neu.edu.ecommerce.dao.ProductDAO;
import neu.edu.ecommerce.email.EcommerceMailService;
import neu.edu.ecommerce.pojo.Consumer;
import neu.edu.ecommerce.pojo.PreOrderProduct;
import neu.edu.ecommerce.pojo.Product;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aelinadas
 */
public class OrderController extends AbstractController {

    private static final Logger logger = Logger.getLogger(OrderController.class);

    public OrderController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("customer") != null) {
            try {
                if (action == "") {
                    mv = new ModelAndView("consumer-home");

                } else if (action.equalsIgnoreCase("viewCart")) {
                    String productIds = request.getParameter("productIds");
                    if (productIds.isEmpty()) {
                        mv = new ModelAndView("customer-error", "message", "Sorry!! something went wrong.");
                    }
                    if (!productIds.isEmpty()) {
                        ArrayList<Integer> idsq = new ArrayList<Integer>();
                        ArrayList<Integer> ids = new ArrayList<Integer>();
                        ArrayList<Integer> quantity = new ArrayList<Integer>();
                        String[] approval = productIds.split(",");
                        for (String string : approval) {
                            idsq.add(Integer.parseInt(string));
                        }

                        for (int i = 0; i < idsq.size(); i++) {
                            if (i % 2 == 0) {
                                ids.add(idsq.get(i));
                            } else {
                                quantity.add(idsq.get(i));
                            }
                        }
                        OrderedProductDAO orderedProductDAO = new OrderedProductDAO();
                        ArrayList<PreOrderProduct> cartItems = new ArrayList<PreOrderProduct>();
                        ProductDAO productDAO = new ProductDAO();
                        for (int i = 0; i < ids.size(); i++) {

                            Product product = productDAO.getProduct(ids.get(i));
                            PreOrderProduct preOrderProduct = new PreOrderProduct();

                            preOrderProduct.setId(product.getId());
                            preOrderProduct.setOrderedQuantity(quantity.get(i));
                            preOrderProduct.setCategory(product.getCategory());
                            preOrderProduct.setProductName(product.getProductName());
                            preOrderProduct.setPrice(product.getPrice());
                            cartItems.add(preOrderProduct);

                        }
                        HashMap productMap = new HashMap<Integer, Integer>();
                        for (PreOrderProduct cartItem : cartItems) {
                            productMap.put(cartItem.getId(), cartItem.getOrderedQuantity());
                        }
                        session.setAttribute("productMap", productMap);
                        mv = new ModelAndView("view-cart", "cartItems", cartItems);
                    }
                } else if (action.equalsIgnoreCase("checkout")) {
                    if (session.getAttribute("productMap") != null) {
                        //System.out.println("insideSession");
                        HashMap<Integer, Integer> h = (HashMap<Integer, Integer>) session.getAttribute("productMap");
                        String remove = request.getParameter("removeList") != null ? request.getParameter("removeList") : "";
                        if (!remove.equalsIgnoreCase("")) {
                            ArrayList<Integer> removalList = new ArrayList<Integer>();
                            String[] removal = remove.split(",");
                            for (String string : removal) {
                                removalList.add(Integer.parseInt(string));
                            }
                            if (!removalList.isEmpty()) {
                                for (Integer integer : removalList) {
                                    h.remove(integer);
                                }
                            }
                            OrderedProductDAO orderedProductDAO = new OrderedProductDAO();
                            double result = orderedProductDAO.finalOrder(h, (Consumer) session.getAttribute("customer"));
                            if (result > 0.0) {
                                Consumer consumer = (Consumer) session.getAttribute("customer");
                                EcommerceMailService ecommerceMailService = new EcommerceMailService();
                                ecommerceMailService.orderConfirmationMail(consumer.getEmail(), consumer.getFname());
                                mv = new ModelAndView("order-success", "message", result);
                            } else {
                                mv = new ModelAndView("customer-error", "message", "Sorry!! Your products cannot be added during this point of time");
                            }
                        } else {
                            OrderedProductDAO orderedProductDAO = new OrderedProductDAO();
                            double result = orderedProductDAO.finalOrder(h, (Consumer) session.getAttribute("customer"));
                            if (result > 0.0) {
                                mv = new ModelAndView("order-success", "message", result);
                            } else {
                                mv = new ModelAndView("customer-error", "message", "Sorry!! Your products cannot be added during this point of time");
                            }
                        }

                    } else {
                        mv = new ModelAndView("customer-error", "message", "Sorry!! Your products cannot be added during this point of time");
                    }

                    //return mv;
                }
            } catch (Exception e) {
                logger.error(e);
                System.out.println(e.getMessage());
                System.out.println("Page not found!");
            }
        } else {
            mv = new ModelAndView("error", "message", "Requested action cannot be performed");
        }

        return mv;
    }

}
