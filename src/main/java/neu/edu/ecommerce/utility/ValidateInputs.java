/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neu.edu.ecommerce.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author aelinadas
 */
public class ValidateInputs {
    public boolean isName(String name) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+([\\s][a-zA-Z]+)*");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public boolean isContact(String contact) {
        Pattern pattern = Pattern.compile("[0-9]{10}");
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }
    public boolean isNumber(String number) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }  
    public boolean isPrice(String price) {
        Pattern pattern = Pattern.compile("[1-9]\\d*(\\.\\d+)?$");
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }
    public boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,63}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean isPassword(String password) {
        Pattern pattern = Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public boolean isAddress(String address) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+([\\s][a-zA-Z0-9]+)*");
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
    public boolean isZipCode(String zipCode) {
        Pattern pattern = Pattern.compile("[0-9]{5}");
        Matcher matcher = pattern.matcher(zipCode);
        return matcher.matches();
    }   
}
