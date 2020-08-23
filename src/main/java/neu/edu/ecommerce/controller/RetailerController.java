/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neu.edu.ecommerce.dao.RetailerDAO;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aelinadas
 */
public class RetailerController extends AbstractController {
    
    public RetailerController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ModelAndView modelAndView = null;
        
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        RetailerDAO retailerdao = new RetailerDAO();
        
        try {
            if (action == null || action == "") {
                modelAndView = new ModelAndView("home");
                return modelAndView;
            }else if (action.equalsIgnoreCase("signup")) {
                modelAndView = new ModelAndView("signup");       
            }else if (action.equalsIgnoreCase("login")) {
                    if(request.getParameter("email") == "email@gmail.com" && request.getParameter("password") == "password"){
                        String loginSuccess = "You have been successfully logged in.";
                        modelAndView = new ModelAndView("success","message", loginSuccess);
                    }else{
                        String loginFailure = "You lost it!!";
                        modelAndView = new ModelAndView("success","message", loginFailure);
                    }
            }else{
                String name = request.getParameter("name");
                String userName = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String confirmpassword = request.getParameter("confirmpassword");
                String contact = request.getParameter("contact");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String country = request.getParameter("country");
                String zipCode = request.getParameter("zipCode");
                
                int result = retailerdao.addNewRetailer(name, userName, email, password, confirmpassword, contact, street, city, state, country, zipCode);
                
                if (result == 1) {
                    String retailerDetails = "You have been successfully filled the form. You will be notified via mail!";
                    modelAndView = new ModelAndView("success","message", retailerDetails);
                } else {
                    modelAndView = new ModelAndView("error", "message", "Not able to add Retailer");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Page not found!");
        }
        return modelAndView;
    }
    
}
