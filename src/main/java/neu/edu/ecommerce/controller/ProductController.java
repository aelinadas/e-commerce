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
import neu.edu.ecommerce.dao.ProductDAO;
import neu.edu.ecommerce.pojo.Product;
import neu.edu.ecommerce.pojo.Retailer;
import neu.edu.ecommerce.utility.ValidateInputs;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author aelinadas
 */
public class ProductController extends AbstractController {

    private static final Logger logger = Logger.getLogger(ProductController.class);

    public ProductController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Retailer retailerAdmin = (Retailer) request.getSession(false).getAttribute("retailer");
        ModelAndView mv = null;
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        try {
            if (action == null || action == "") {
                mv = new ModelAndView("retailer-home");
            } else if (action.equalsIgnoreCase("addproduct")) {
                mv = new ModelAndView("add-product");
            } else if (action.equalsIgnoreCase("viewproduct")) {
                ProductDAO productDAO = new ProductDAO();
                ArrayList<Product> products = productDAO.getAllProducts(retailerAdmin);
                mv = new ModelAndView("view-list-of-products", "products", products);
            } else if (action.equals("delete") && retailerAdmin != null) {
                ProductDAO productDAO = new ProductDAO();
                String productId = request.getParameter("id") != null ? request.getParameter("id") : "";
                if (!productId.equalsIgnoreCase("")) {
                    int result = productDAO.deleteProduct(Integer.parseInt(productId));
                    if (result == 1) {
                        ArrayList<Product> products = productDAO.getAllProducts(retailerAdmin);
                        mv = new ModelAndView("view-list-of-products", "products", products);
                    } else {
                        mv = new ModelAndView("retailer-error", "message", "Product you requested for does not exist");
                    }
                } else {
                    mv = new ModelAndView("retailer-error", "message", "Product can't be deleted");
                }
            } else if (action.equalsIgnoreCase("update")) {
                String productId = request.getParameter("id") != null ? request.getParameter("id") : "";
                if (!productId.equalsIgnoreCase("")) {
                    ProductDAO productDAO = new ProductDAO();
                    Product product = productDAO.getProduct(Integer.parseInt(productId));
                    if (product != null) {
                        mv = new ModelAndView("update-product", "product", product);
                    } else {
                        mv = new ModelAndView("retailer-error", "message", "Product you requested for does not exist");
                    }
                }
            } else if (action.equalsIgnoreCase("updateproduct") && retailerAdmin != null) {
                String id = request.getParameter("id") != null ? request.getParameter("id") : "";
                String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                String category = request.getParameter("category") != null ? request.getParameter("category") : "";
                String description = request.getParameter("description") != null ? request.getParameter("description") : "";
                String quantity = request.getParameter("quantity") != null ? request.getParameter("quantity") : "";
                String retailerName = request.getParameter("retailer") != null ? request.getParameter("retailer") : "";
                String price = request.getParameter("price") != null ? request.getParameter("price") : "";
                String numberOfProduct = request.getParameter("numberOfProduct") != null ? request.getParameter("numberOfProduct") : "";
                String numberOfProductPerCustomer = request.getParameter("numberOfProductPerCustomer") != null ? request.getParameter("numberOfProductPerCustomer") : "";
                if (!id.equalsIgnoreCase("") && !name.equalsIgnoreCase("") && !category.equalsIgnoreCase("") && !description.equalsIgnoreCase("") && !quantity.equalsIgnoreCase("")
                        && !retailerName.equalsIgnoreCase("") && !price.equalsIgnoreCase("") && !numberOfProduct.equalsIgnoreCase("") && !numberOfProductPerCustomer.equalsIgnoreCase("")) {
                    ValidateInputs vi = new ValidateInputs();
                    if (vi.isAddress(name) && vi.isName(category) && vi.isAddress(description) && vi.isNumber(quantity) && vi.isName(retailerName) && vi.isPrice(price) && vi.isNumber(numberOfProduct) && vi.isNumber(numberOfProductPerCustomer) && vi.isNumber(id)) {
                        ProductDAO productDAO = new ProductDAO();
                        int result = productDAO.updateProduct(Integer.parseInt(id), name, Double.parseDouble(price), description, category, Integer.parseInt(quantity), retailerName, Integer.parseInt(numberOfProduct), Integer.parseInt(numberOfProductPerCustomer), retailerAdmin);
                        if (result == 1) {
                            mv = new ModelAndView("retailer-success", "message", "Your product has been successfully updated");
                        } else {
                            mv = new ModelAndView("retailer-error", "message", "Product cannot be updated at this point of time");
                        }
                    } else {
                        mv = new ModelAndView("retailer-error", "message", "Cannot be updated at this point of time");
                    }
                } else {
                    mv = new ModelAndView("retailer-error", "message", "Cannot be updated at this point of time");
                }
            } else if (action.equalsIgnoreCase("productadd")) {
                ProductDAO productDAO = new ProductDAO();
                String name = request.getParameter("name") == null ? "" : request.getParameter("name");
                String category = request.getParameter("category") == null ? "" : request.getParameter("category");
                String description = request.getParameter("description") == null ? "" : request.getParameter("description");
                String quantity = request.getParameter("quantity") == null ? "" : request.getParameter("quantity");
                String retailerName = request.getParameter("retailer") == null ? "" : request.getParameter("retailer");
                String price = request.getParameter("price") == null ? "" : request.getParameter("price");
                String numberOfProduct = request.getParameter("numberOfProduct") == null ? "" : request.getParameter("numberOfProduct");
                String numberOfProductPerCustomer = request.getParameter("numberOfProductPerCustomer") == null ? "" : request.getParameter("numberOfProductPerCustomer");
                if (!name.equalsIgnoreCase("") && !category.equalsIgnoreCase("") && !description.equalsIgnoreCase("") && !quantity.equalsIgnoreCase("")
                        && !retailerName.equalsIgnoreCase("") && !price.equalsIgnoreCase("") && !numberOfProduct.equalsIgnoreCase("") && !numberOfProductPerCustomer.equalsIgnoreCase("")) {
                    ValidateInputs vi = new ValidateInputs();
                    if (vi.isAddress(name) && vi.isName(category) && vi.isAddress(description) && vi.isNumber(quantity) && vi.isName(retailerName) && vi.isPrice(price) && vi.isNumber(numberOfProduct) && vi.isNumber(numberOfProductPerCustomer)) {
                        int result = productDAO.addProduct(name, Double.parseDouble(price), description, category, Integer.parseInt(quantity), retailerName, Integer.parseInt(numberOfProduct), Integer.parseInt(numberOfProductPerCustomer), retailerAdmin);
                        if (result == 1) {
                            String productDetails = "You have been successfully added a product.";
                            mv = new ModelAndView("retailer-success", "message", productDetails);
                        } else {
                            mv = new ModelAndView("retailer-error", "message", "Not able to add Product");
                        }
                    } else {
                        mv = new ModelAndView("retailer-error", "message", "Not able to add Product");
                    }
                } else {
                    mv = new ModelAndView("retailer-error", "message", "Not able to add Product");
                }
            } else if (action.equalsIgnoreCase("validateProduct")) {
                String productName = request.getParameter("productname") != null ? request.getParameter("productname") : "";
                if (!productName.equalsIgnoreCase("")) {
                    ProductDAO productDAO = new ProductDAO();
                    Boolean exist = productDAO.isUnique(productName);
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
            System.out.println(e.getMessage());
        }
        return mv;
    }

}
