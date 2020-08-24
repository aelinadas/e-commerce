# E-Commerce Website
<img alt="home" src="https://github.com/aelinadas/e-commerce/blob/master/images/home.png" />

#### Incorporate an e-commerce website where customers can place orders for items displayed on the site. E-commerce website allows new retailers to enroll themselves and place their products in the catalog.

## Languages, Tools and Servers used

<img align="left" alt="java" width="50px" height="60px" src="https://github.com/aelinadas/aelinadas/blob/master/images/java.png" />
<img align="left" alt="spring" width="50px" height="40px" src="https://github.com/aelinadas/aelinadas/blob/master/images/spring.png" />
<img align="left" alt="mysql" width="60px" height="60px" src="https://github.com/aelinadas/aelinadas/blob/master/images/mysql.png" />
<img align="left" alt="netbeans" width="50px" height="50px" src="https://github.com/aelinadas/aelinadas/blob/master/images/netbeans.png" />
<img align="left" alt="tomcat" width="50px" height="50px" src="https://github.com/aelinadas/aelinadas/blob/master/images/tomcat.jpg" />
<br />
<br />

## Application Features

1. Admin is given privilege to approve or reject the retailers based on the business they want to be into

    <img alt="approve" src="https://github.com/aelinadas/e-commerce/blob/master/images/approve.png" />

    <img alt="disapprove" src="https://github.com/aelinadas/e-commerce/blob/master/images/disapprove.png" />

2. Admin can view the details of the products sold so far

    <img alt="booking" src="https://github.com/aelinadas/e-commerce/blob/master/images/sold.png" />

3. An activation mail is triggered to the Retailer upon account approval

    <img alt="mail" src="https://github.com/aelinadas/e-commerce/blob/master/images/emailapprove.png" />

4. Retailer can add products that he wants to sell and can manage the same

    <img alt="addproduct" src="https://github.com/aelinadas/e-commerce/blob/master/images/addproduct.png" />

    <img alt="manageproduct" src="https://github.com/aelinadas/e-commerce/blob/master/images/manageproduct.png" />

5. Customers can search products that they want to purchase by selecting category from the dropdown or can directly browse with the product name

    <img alt="browse" src="https://github.com/aelinadas/e-commerce/blob/master/images/browse.png" />

    <img alt="result" src="https://github.com/aelinadas/e-commerce/blob/master/images/result.png" />

6. If based on search no product is found then view all products will show the list of products

    <img alt="viewall" src="https://github.com/aelinadas/e-commerce/blob/master/images/viewall.png" />

7. View all products will show all the products by all retailers and if the customer adds the product to cart, then it will be added to the cart

    <img alt="cart" src="https://github.com/aelinadas/e-commerce/blob/master/images/cart.png" />

8. Upon Checkout, an order confirmation email will be triggered to the customer with the total amount charged

    <img alt="orderconfirm" src="https://github.com/aelinadas/e-commerce/blob/master/images/orderconfirm.png" />

9. A downloadable invoice of the order is generated that be accesed within the customer account under `Order History`

    <img alt="order" src="https://github.com/aelinadas/e-commerce/blob/master/images/order.png" />

    <img alt="invoice" src="https://github.com/aelinadas/e-commerce/blob/master/images/invoice.png" />

10. User passwords are encrypted, leveraging BCryt, before storing user details

    <img alt="encrypt" src="https://github.com/aelinadas/e-commerce/blob/master/images/encrypt.png" />

## Application Deployment

- Ensure that you have installed Java (Java 8 or above) on your machine
- Clone or download this repository on to your machine
- Install NetBeans IDE, if not present
- Import the cloned or downloaded project
- Download and install Apache Tomcat 8 or above
- Integrate Tomcat with Netbeans and run the application
                    OR
  build and generate a WAR file of the application and deploy it in the tomcat server by placing the previously generated WAR file in `webapps` folder of the server                  
- Register a Retailer and a Customer
- Login as Admin, enter Username and Password as `admin@gmail.com` and `admin` respectively and approve/reject registered retailer