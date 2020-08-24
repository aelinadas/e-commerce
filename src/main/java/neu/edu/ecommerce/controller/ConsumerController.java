/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.controller;

import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import neu.edu.ecommerce.dao.ConsumerDAO;
import neu.edu.ecommerce.dao.OrderedProductDAO;
import neu.edu.ecommerce.pojo.Consumer;
import neu.edu.ecommerce.utility.ValidateInputs;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aelinadas
 */
public class ConsumerController extends AbstractController {

    private static final Logger logger = Logger.getLogger(ConsumerController.class);

    public ConsumerController() {
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView modelAndView = null;

        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        try {
            if (action == null || action == "") {
                modelAndView = new ModelAndView("consumer-home");
                return modelAndView;
            } else if (action.equalsIgnoreCase("updateprofile")) {
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("customer") != null) {
                    modelAndView = new ModelAndView("update-consumer");
                } else {
                    modelAndView = new ModelAndView("customer-error", "message", "Update function requested is not available");
                }
            } else if (action.equalsIgnoreCase("updateCustomer")) {
                ConsumerDAO consumerdao = new ConsumerDAO();
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("customer") != null) {
                    Consumer consumer = (Consumer) session.getAttribute("customer");
                    String fname = request.getParameter("fname") == null ? "" : request.getParameter("fname");
                    String lname = request.getParameter("lname") == null ? "" : request.getParameter("lname");
                    String age = request.getParameter("age") == null ? "" : request.getParameter("age");
                    String contact = request.getParameter("phoneNumber") == null ? "" : request.getParameter("phoneNumber");
                    String street = request.getParameter("street") == null ? "" : request.getParameter("street");
                    String city = request.getParameter("city") == null ? "" : request.getParameter("city");
                    String state = request.getParameter("state") == null ? "" : request.getParameter("state");
                    String country = request.getParameter("country") == null ? "" : request.getParameter("country");
                    String zipCode = request.getParameter("zipCode") == null ? "" : request.getParameter("zipCode");
                    if (!fname.equalsIgnoreCase("") && !lname.equalsIgnoreCase("") && !age.equalsIgnoreCase("") && !contact.equalsIgnoreCase("")
                            && !street.equalsIgnoreCase("") && !city.equalsIgnoreCase("") && !state.equalsIgnoreCase("") && !country.equalsIgnoreCase("") && !zipCode.equalsIgnoreCase("")) {
                        ValidateInputs vi = new ValidateInputs();
                        if (vi.isName(fname) && vi.isName(lname) && vi.isNumber(age) && vi.isContact(contact) && vi.isAddress(street) && vi.isName(city) && vi.isName(state) && vi.isName(country) && vi.isZipCode(zipCode) && Integer.parseInt(age) > 1 && Integer.parseInt(age) < 150) {
                            int result = consumerdao.updateConsumer(consumer, fname, lname, Integer.parseInt(age), contact, street, city, state, country, zipCode);
                            if (result == 1) {
                                modelAndView = new ModelAndView("customer-success", "message", fname + "!! Your information has been successfully updated");
                            } else {
                                modelAndView = new ModelAndView("customer-error", "message", "Sorry " + fname + " cannot be updated at this point of time");
                            }
                        } else {
                            modelAndView = new ModelAndView("customer-error", "message", "Cannot be updated at this point of time");
                        }

                    } else {
                        modelAndView = new ModelAndView("customer-error", "message", "Cannot be updated at this point of time");
                    }

                } else {
                    String msg = "Cannot be updated at this point of time";
                    modelAndView = new ModelAndView("customer-error", "message", msg);
                }
            } else if (action.equalsIgnoreCase("vieworder")) {
                Consumer consumer = (Consumer) request.getSession(false).getAttribute("customer");
                OrderedProductDAO orderedProductDAO = new OrderedProductDAO();
                if (consumer != null) {
                    int id = consumer.getId();
                    ArrayList<Consumer> orderedProducts = orderedProductDAO.getOrder(id);
                    modelAndView = new ModelAndView("view-order", "orderCart", orderedProducts);
                }
            } else if (action.equalsIgnoreCase("placeorder")) {
                modelAndView = new ModelAndView("place-order");
            } else if (action.equalsIgnoreCase("validateConsumer")) {
                String email = request.getParameter("email") != null ? request.getParameter("email") : "";
                if (!email.equalsIgnoreCase("")) {
                    ConsumerDAO consumerDAO = new ConsumerDAO();
                    Boolean exist = consumerDAO.isUnique(email);
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
            } else if (action.equalsIgnoreCase("consumerSignup")) {
                ConsumerDAO consumerdao = new ConsumerDAO();
                String fname = request.getParameter("fname") == null ? "" : request.getParameter("fname");
                String lname = request.getParameter("lname") == null ? "" : request.getParameter("lname");
                String age = request.getParameter("age") == null ? "" : request.getParameter("age");
                String email = request.getParameter("email") == null ? "" : request.getParameter("email");
                String phoneNumber = request.getParameter("contact") == null ? "" : request.getParameter("contact");
                String password = request.getParameter("password") == null ? "" : request.getParameter("password");
                String street = request.getParameter("street") == null ? "" : request.getParameter("street");
                String city = request.getParameter("city") == null ? "" : request.getParameter("city");
                String state = request.getParameter("state") == null ? "" : request.getParameter("state");
                String country = request.getParameter("country") == null ? "" : request.getParameter("country");
                String zipCode = request.getParameter("zipCode") == null ? "" : request.getParameter("zipCode");
                if (!fname.equalsIgnoreCase("") && !lname.equalsIgnoreCase("") && !age.equalsIgnoreCase("") && !phoneNumber.equalsIgnoreCase("")
                        && !email.equalsIgnoreCase("") && !password.equalsIgnoreCase("") && !street.equalsIgnoreCase("") && !city.equalsIgnoreCase("")
                        && !state.equalsIgnoreCase("") && !country.equalsIgnoreCase("") && !zipCode.equalsIgnoreCase("")) {
                    ValidateInputs vi = new ValidateInputs();
                    if (vi.isName(fname) && vi.isName(lname) && vi.isNumber(age) && vi.isEmail(email) && vi.isContact(phoneNumber) && vi.isPassword(password) && vi.isAddress(street) && vi.isName(city) && vi.isName(state) && vi.isName(country) && vi.isZipCode(zipCode) && Integer.parseInt(age) > 1 && Integer.parseInt(age) < 150) {
                        int result = consumerdao.addNewConsumer(fname, lname, Integer.parseInt(age), phoneNumber, email, password, street, city, state, country, zipCode);
                        if (result == 1) {
                            String retailerDetails = fname + "!! You have been successfully registered with us.";
                            modelAndView = new ModelAndView("success", "message", retailerDetails);
                        } else {
                            modelAndView = new ModelAndView("error", "message", "Not able to add Consumer");
                        }
                    } else {
                        modelAndView = new ModelAndView("error", "message", "Not able to add Consumer");
                    }
                } else {
                    modelAndView = new ModelAndView("error", "message", "Not able to add Consumer");
                }
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println("Page not found!");
        }
        return modelAndView;
    }
}
