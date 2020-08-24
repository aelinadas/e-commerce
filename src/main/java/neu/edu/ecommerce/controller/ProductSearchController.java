/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import neu.edu.ecommerce.dao.ProductDAO;
import neu.edu.ecommerce.pojo.Product;
import neu.edu.ecommerce.utility.ValidateInputs;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aelinadas
 */
public class ProductSearchController extends AbstractController {
    
    private static final Logger logger = Logger.getLogger(ProductSearchController.class);
    
    public ProductSearchController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = null;
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        try {
            if (action == null || action == "") {
                modelAndView = new ModelAndView("consumer-home");
                return modelAndView;
            } else if (action.equalsIgnoreCase("viewproducts")) {
                ProductDAO pdao = new ProductDAO();
                ArrayList<String> categoryList = pdao.getCategory();
                modelAndView = new ModelAndView("search-products", "categories", categoryList);
            } else if (action.equalsIgnoreCase("viewAllProducts")) {
                ProductDAO pdao = new ProductDAO();
                ArrayList<Product> productList = pdao.getAllProducts();
                modelAndView = new ModelAndView("search-results", "productList", productList);
            } else if (action.equalsIgnoreCase("searchProduct")) {
                String productName = request.getParameter("productName") != null ? request.getParameter("productName") : "";
                String category = request.getParameter("category") != null ? request.getParameter("category") : "";
                if (!productName.equalsIgnoreCase("") && !category.equalsIgnoreCase("")) {
                    ValidateInputs vi = new ValidateInputs();
                    if (vi.isName(productName) && vi.isName(category)) {
                        ProductDAO productDAO = new ProductDAO();
                        ArrayList<Product> productList = productDAO.searchProducts(productName, category);
                        modelAndView = new ModelAndView("search-results", "productList", productList);
                    } else {
                        modelAndView = new ModelAndView("customer-error", "message", "Sorry no results found");
                    }
                } else {
                    modelAndView = new ModelAndView("customer-error", "message", "Sorry no results found");
                }
            }
        } catch (Exception e) {
            logger.error(e);
            System.out.println("Page not found!");
        }
        return modelAndView;
    }
}
