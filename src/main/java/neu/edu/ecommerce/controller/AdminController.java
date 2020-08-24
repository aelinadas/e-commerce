/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neu.edu.ecommerce.dao.OrderedProductDAO;
import neu.edu.ecommerce.dao.RetailerDAO;
import neu.edu.ecommerce.email.EcommerceMailService;
import neu.edu.ecommerce.pojo.OrderedProduct;
import neu.edu.ecommerce.pojo.Retailer;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aelinadas
 */
public class AdminController extends AbstractController {

    private static final Logger logger = Logger.getLogger(AdminController.class);

    public AdminController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        RetailerDAO retailerdao = new RetailerDAO();
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        try {
            if (action == null || action == "") {
                mv = new ModelAndView("admin-home");
            } else if (action.equalsIgnoreCase("approveORreject")) {
                RetailerDAO retailerDAO = new RetailerDAO();
                ArrayList<Retailer> retailers = retailerDAO.getRetailers();
                mv = new ModelAndView("approve-retailers", "retailers", retailers);
            } else if (action.equalsIgnoreCase("viewOrders")) {
                OrderedProductDAO orderedProductDAO = new OrderedProductDAO();
                ArrayList<OrderedProduct> product = orderedProductDAO.getAllOrder();
                mv = new ModelAndView("sold-products", "products", product);
            } else if (action.equalsIgnoreCase("approveRetailer")) {
                RetailerDAO retailerDAO = new RetailerDAO();
                String id = request.getParameter("id") == null ? "" : request.getParameter("id");
                if (!id.equalsIgnoreCase("")) {
                    int result =0;
                    result = retailerdao.activateRetailer(Integer.parseInt(id));
                    if (result == 1) {
                        Retailer retailer = retailerdao.getRetailer(Integer.parseInt(id));
                        EcommerceMailService ecommerceMailService = new EcommerceMailService();
                        ecommerceMailService.userActivationMail(retailer.getEmail(), retailer.getName());
                        ArrayList<Retailer> retailers = retailerDAO.getRetailers();
                        mv = new ModelAndView("approve-retailers", "retailers", retailers);
                    } else {
                        mv = new ModelAndView("admin-error", "message", "Retailer can not be approved at this point of time");
                    }
                } else {
                    mv = new ModelAndView("admin-error", "message", "Retailer can not be approved at this point of time");
                }
            } else if (action.equalsIgnoreCase("rejectRetailer")) {
                RetailerDAO retailerDAO = new RetailerDAO();
                String id = request.getParameter("id") == null ? "" : request.getParameter("id");
                if (!id.equalsIgnoreCase("")) {
                    int result = retailerdao.deactivateRetailers(Integer.parseInt(id));
                    if (result == 1) {
                        ArrayList<Retailer> retailers = retailerDAO.getRetailers();
                        mv = new ModelAndView("approve-retailers", "retailers", retailers);
                    } else {
                        mv = new ModelAndView("admin-error", "message", "Retailer can not be deleted at this point of time");
                    }
                } else {
                    mv = new ModelAndView("admin-error", "message", "Retailer can not be deleted at this point of time");
                }
            } else if (action.equals("deactivate")) {
                RetailerDAO retailerDAO = new RetailerDAO();
                ArrayList<Retailer> requestList = retailerDAO.getApprovedRetailerList();
                mv = new ModelAndView("delete-retailer-accounts", "requestList", requestList);
            } else if (action.equals("deleteAccounts")) {
                String[] id = request.getParameterValues("deleteRetailer");
                int result = 0;
                if (id.length > 0) {
                    RetailerDAO retailerDAO = new RetailerDAO();
                    for (String deleteID : id) {
                        result = retailerDAO.deleteAccount(Integer.parseInt(deleteID));
                        if (result == 0) {
                            break;
                        }
                    }
                    if (result == 1) {
                        mv = new ModelAndView("admin-home");
                    } else {
                        mv = new ModelAndView("admin-error", "message", "Cannot delete the account for time being");
                    }
                } else {
                    mv = new ModelAndView("admin-error", "message", "Cannot delete the account for time being");
                }
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println("Page not found!");
        }
        return mv;
    }

}
