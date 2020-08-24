/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aelinadas
 */
public class HomeController extends AbstractController {
    
    private static final Logger logger = Logger.getLogger(HomeController.class);
    
    public HomeController() {
    }
    
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = null;  
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        try{
            if (action == null || action == "") {
                mv = new ModelAndView("home");
                return mv;
            }else if (action.equalsIgnoreCase("customerlogin")) {
                mv = new ModelAndView("consumer-login");
            }else if (action.equalsIgnoreCase("customersignup")) {
                mv = new ModelAndView("consumer-signup");
            }else if (action.equalsIgnoreCase("login")) {
                mv = new ModelAndView("retailer-login");
            }else if (action.equalsIgnoreCase("signup")) {
                mv = new ModelAndView("retailer-signup");
            }else if (action.equalsIgnoreCase("adminlogin")) {
                mv = new ModelAndView("admin-login");
            }else if (action.equalsIgnoreCase("logout")) {
                HttpSession session = request.getSession(false);
                if (session!=null) {
                    session.invalidate();
                }
                mv = new ModelAndView("home");
            }
        }catch(Exception e){
            logger.error(e);
            System.out.println("Page not found!");
        }
        return mv;
    }
}
