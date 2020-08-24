/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.controller;

import com.google.gson.JsonObject;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import neu.edu.ecommerce.dao.RetailerDAO;
import neu.edu.ecommerce.pojo.Retailer;
import neu.edu.ecommerce.utility.ValidateInputs;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aelinadas
 */
public class RetailerController extends AbstractController {

    private static final Logger logger = Logger.getLogger(RetailerController.class);

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
                modelAndView = new ModelAndView("retailer-home");
                return modelAndView;
            } else if (action.equalsIgnoreCase("updateView")) {
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("retailer") != null) {
                    modelAndView = new ModelAndView("update-retailer");
                } else {
                    modelAndView = new ModelAndView("retailer-error", "message", "Update function requested is not available");
                }
            } else if (action.equalsIgnoreCase("addproduct")) {
                modelAndView = new ModelAndView("add-product");
            } else if (action.equalsIgnoreCase("validateRetailer")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                if (!email.equalsIgnoreCase("")) {
                    RetailerDAO retailerDAO = new RetailerDAO();
                    Boolean exist = retailerDAO.isUnique(email);
                    JsonObject result = new JsonObject();
                    if (exist) {
                        result.addProperty("exists", Boolean.TRUE);
                    } else {
                        result.addProperty("exists", Boolean.FALSE);
                    }
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(result);
                    out.flush();
                }
            } else if (action.equalsIgnoreCase("updateRetailer")) {
                RetailerDAO retailerDAO = new RetailerDAO();
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("retailer") != null) {
                    Retailer retailer = (Retailer) session.getAttribute("retailer");
                    String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                    String username = request.getParameter("username") != null ? request.getParameter("username") : "";
                    String contact = request.getParameter("contact") != null ? request.getParameter("contact") : "";
                    String street = request.getParameter("street") != null ? request.getParameter("street") : "";
                    String city = request.getParameter("city") != null ? request.getParameter("city") : "";
                    String state = request.getParameter("state") != null ? request.getParameter("state") : "";
                    String country = request.getParameter("country") != null ? request.getParameter("country") : "";
                    String zipCode = request.getParameter("zipCode") != null ? request.getParameter("zipCode") : "";

                    if (!name.equalsIgnoreCase("") && !username.equalsIgnoreCase("") && !contact.equalsIgnoreCase("") && !street.equalsIgnoreCase("") && !city.equalsIgnoreCase("")
                            && !state.equalsIgnoreCase("") && !country.equalsIgnoreCase("") && !zipCode.equalsIgnoreCase("")) {
                        ValidateInputs vi = new ValidateInputs();
                        if (vi.isName(name) && vi.isName(username) && vi.isContact(contact) && vi.isAddress(street) && vi.isName(city) && vi.isName(state) && vi.isName(country) && vi.isZipCode(zipCode)) {
                            int result = retailerdao.updateRetailer(retailer, name, username, contact, street, city, state, country, zipCode);
                            if (result == 1) {
                                modelAndView = new ModelAndView("retailer-success", "message", "Congratulations " + username + "!! Your information has been successfully updated");
                            } else {
                                modelAndView = new ModelAndView("retailer-error", "message", "Sorry " + username + "cannot be updated at this point of time");
                            }
                        } else {
                            modelAndView = new ModelAndView("retailer-error", "message", "Sorry " + username + "cannot be updated at this point of time");
                        }
                    } else {
                        modelAndView = new ModelAndView("retailer-error", "message", "Sorry " + username + "cannot be updated at this point of time");
                    }
                } else {
                    modelAndView = new ModelAndView("retailer-error", "message", "Cannot be updated at this point of time");
                }
            } else if (action.equalsIgnoreCase("signupRetailer")) {
                String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                String userName = request.getParameter("username") != null ? request.getParameter("username") : "";
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                String password = request.getParameter("password") != null ? request.getParameter("password") : "";
                String contact = request.getParameter("contact") != null ? request.getParameter("contact") : "";
                String street = request.getParameter("street") != null ? request.getParameter("street") : "";
                String city = request.getParameter("city") != null ? request.getParameter("city") : "";
                String state = request.getParameter("state") != null ? request.getParameter("state") : "";
                String country = request.getParameter("country") != null ? request.getParameter("country") : "";
                String zipCode = request.getParameter("zipCode") != null ? request.getParameter("zipCode") : "";
                if (!name.equalsIgnoreCase("") && !userName.equalsIgnoreCase("") && !email.equalsIgnoreCase("") && !password.equalsIgnoreCase("") && !contact.equalsIgnoreCase("") && !street.equalsIgnoreCase("") && !city.equalsIgnoreCase("")
                        && !state.equalsIgnoreCase("") && !country.equalsIgnoreCase("") && !zipCode.equalsIgnoreCase("")) {
                    ValidateInputs vi = new ValidateInputs();
                    if (vi.isName(name) && vi.isName(userName) && vi.isEmail(email) && vi.isPassword(password) && vi.isContact(contact) && vi.isAddress(street) && vi.isName(city) && vi.isName(state) && vi.isName(country) && vi.isZipCode(zipCode)) {
                        int result = retailerdao.addNewRetailer(name, userName, email, password, contact, street, city, state, country, zipCode);
                        if (result == 1) {
                            String retailerDetails = "You have been successfully filled the form. You will be notified via mail.";
                            modelAndView = new ModelAndView("success", "message", retailerDetails);
                        } else {
                            modelAndView = new ModelAndView("error", "message", "Not able to add Retailer");
                        }
                    } else {
                        modelAndView = new ModelAndView("error", "message", "Not able to add Retailer");
                    }
                } else {
                    modelAndView = new ModelAndView("error", "message", "Not able to add Retailer");
                }

            }else if (action.equalsIgnoreCase("validateRetailerName")) {
                String retailer = request.getParameter("retailer") != null ? request.getParameter("retailer") : "";
                if (!retailer.equalsIgnoreCase("")) {
                    RetailerDAO retailerDAO = new RetailerDAO();
                    Boolean exist = retailerDAO.isNameUnique(retailer);
                    JsonObject result = new JsonObject();
                    if (exist) {
                        result.addProperty("exists", Boolean.TRUE);
                    } else {
                        result.addProperty("exists", Boolean.FALSE);
                    }
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(result);
                    out.flush();
                }
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println("Page not found!");
        }
        return modelAndView;
    }

}
