/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neu.edu.ecommerce.dao.OrderedProductDAO;
import neu.edu.ecommerce.pojo.OrderedProduct;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aelinadas
 */
public class DownloadOrderController extends AbstractController {
    
    public DownloadOrderController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        try {
            String id = request.getParameter("id") != null ? request.getParameter("id") : "";
            if (!id.equalsIgnoreCase("")) {
                OrderedProductDAO orderedProductDAO = new OrderedProductDAO();
                OrderedProduct orderedProduct=orderedProductDAO.getOrderById(Integer.parseInt(id));
                if (orderedProduct != null) {
                    Map<String, String> orderdata = new LinkedHashMap<String, String>();
                    orderdata.put("Order Number", orderedProduct.getOrderNumber());
                    orderdata.put("Order Date", orderedProduct.getOrderDateTime() + "");
                    orderdata.put("Product Name", orderedProduct.getProductName());
                    orderdata.put("Product Category", orderedProduct.getCategory());
                    orderdata.put("Product Description", orderedProduct.getDescription());
                    orderdata.put("Product Price", orderedProduct.getPrice() + "");
                    orderdata.put("Product Quantity", orderedProduct.getQuantity()+"");
                    orderdata.put("Retailer Name", orderedProduct.getRetailerName());
                    mv = new ModelAndView("orderPdf", "downloadPdf", orderdata);
                } else {
                    mv = new ModelAndView("customer-error", "message", "Sorry, requested order does not exist");
                }
            } else {
                mv = new ModelAndView("customer-error", "message", "Sorry, requested order does not exist");
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println(e.getMessage());
        }
        return mv;
    }
    
}
