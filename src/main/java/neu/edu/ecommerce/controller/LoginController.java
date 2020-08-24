/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import neu.edu.ecommerce.dao.ConsumerDAO;
import neu.edu.ecommerce.dao.RetailerDAO;
import neu.edu.ecommerce.pojo.Consumer;
import neu.edu.ecommerce.pojo.Retailer;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aelinadas
 */
public class LoginController extends AbstractController{
    private static final Logger logger = Logger.getLogger(LoginController.class);
    public LoginController(){
    }
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ModelAndView mv = null;
        
        try {
            String action = request.getParameter("action") != null ? request.getParameter("action") : "";
            if (action.equalsIgnoreCase("retailerLogin")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                String password = request.getParameter("password") != null ? request.getParameter("password") : "";
                if (!email.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
                    RetailerDAO retailerDAO = new RetailerDAO();
                    Retailer retailer = retailerDAO.authenticateRetailer(email, password);
                    if (retailer != null) {
                        if (retailer.getStatus() != Byte.MIN_VALUE) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("retailer", retailer);
                            mv = new ModelAndView("retailer-home");
                        } else {
                            mv = new ModelAndView("error", "message", "Your account is not active. Please contact customer care.");
                        }
                    } else {
                        mv = new ModelAndView("error", "message", "Please recheck your Username and Password");
                    }
                } else {
                    mv = new ModelAndView("error", "message", "Please recheck your Username and Password");
                }
            }
            if (action.equalsIgnoreCase("consumerLogin")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                String password = request.getParameter("password") != null ? request.getParameter("password") : "";
                if (!email.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
                    ConsumerDAO consumerDAO = new ConsumerDAO();
                    Consumer customer = consumerDAO.authenticateConsumer(email, password);
                    if (customer != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("customer", customer);
                        mv = new ModelAndView("consumer-home");
                    } else {
                        mv = new ModelAndView("error", "message", "Please recheck your Username and Password");
                    }
                } else {
                    mv = new ModelAndView("error", "message", "Please recheck your Username and Password");
                }
            }
            if (action.equalsIgnoreCase("adminLogin")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                String password = request.getParameter("password") != null ? request.getParameter("password") : "";
                if (!email.equalsIgnoreCase("") && !password.equalsIgnoreCase("")) {
                    if (email.equals("admin@gmail.com") && password.equals("admin")) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("admin", "admin");
                        mv = new ModelAndView("admin-home");
                    } else {
                        mv = new ModelAndView("error", "message", "Please recheck your Username and Password");
                    }
                } else {
                    mv = new ModelAndView("error", "message", "Please recheck your Username and Password");
                }
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }
        return mv;
    }
}
