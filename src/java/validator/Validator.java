/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Stephane Ehu Alias RigtheousByGod 
 */
public class Validator {

    // ensures that quantity input is number between 0 and 99
    // applies to quantity fields in cart page
    public boolean validateQuantity(String productId, String quantity) {

        boolean errorFlag = false;

        if (!productId.isEmpty() && !quantity.isEmpty()) {

            int i = -1;

            try {

                i = Integer.parseInt(quantity);
            } catch (NumberFormatException nfe) {

                System.out.println("User did not enter a number in the quantity field");
            }

            if (i < 0 || i > 99) {

                errorFlag = true;
            }
        }

        return errorFlag;
    }



    public boolean validateLoginForm(String email,
            String password, HttpServletRequest request) {
        boolean errorloginFlag = false;

        boolean emailidError;
        boolean psswordError;

        if (email == null
                || email.equals("")
                || !email.contains("@")
                || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errorloginFlag = true;
            emailidError = true;
            request.setAttribute("emailidError", emailidError);
        }

        if (password == null
                || password.equals("")
                || password.length() > 15) {
            errorloginFlag = true;
            psswordError = true;
            request.setAttribute("psswordError", psswordError);
        }

        return errorloginFlag;
    }

    public boolean validateRegisterForm(String firstname,
            String lastname,
            String email,
            String phone,
            String address,
            String password,
            String confirm,
            HttpServletRequest request) {
        boolean firstnameError;
        boolean lastnameError;
        boolean emailError;
        boolean phoneError;
        boolean addressError;
        boolean passwordError;
        boolean confirmError;

        boolean errorFlag = false;
        if (firstname == null
                || firstname.equals("")
                || firstname.length() > 45) {
            errorFlag = true;
            firstnameError = true;
            request.setAttribute("firstnameError", firstnameError);
        }

        if (lastname == null
                || lastname.equals("")
                || lastname.length() > 90) {
            errorFlag = true;
            lastnameError = true;
            request.setAttribute("lastnameError", lastnameError);
        }

        if (email == null
                || email.equals("")
                || !email.contains("@")
                || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errorFlag = true;
            emailError = true;
            request.setAttribute("emailError", emailError);
        }
        if (phone == null
                || phone.equals("")
                || phone.matches("^[0-9]*$")) {
            errorFlag = true;
            phoneError = true;
            request.setAttribute("phoneError", phoneError);
        }
        if (address == null
                || address.equals("")
                || address.length() > 130) {
            errorFlag = true;
            addressError = true;
            request.setAttribute("addressError", addressError);
        }

        if (password == null
                || password.equals("")
                || password.length() > 15) {
            errorFlag = true;
            passwordError = true;
            request.setAttribute("passwordError", passwordError);
        }

        if (confirm == null
                || !confirm.equals(password)) {
            errorFlag = true;
            confirmError = true;
            request.setAttribute("confirmError", confirmError);
        }
          

        return errorFlag;
    }

    public boolean validateUpdateForm(String firstname,
            String lastname,
            String email,
            String phone,
            String address,
            String password,
            HttpServletRequest request) {
        boolean firstnameError;
        boolean lastnameError;
        boolean emailError;
        boolean phoneError;
        boolean addressError;
        boolean passwordError;
       

        boolean errorFlag = false;
        if (firstname == null
                || firstname.equals("")
                || firstname.length() > 45) {
            errorFlag = true;
            firstnameError = true;
            request.setAttribute("firstnameError", firstnameError);
        }

        if (lastname == null
                || lastname.equals("")
                || lastname.length() > 90) {
            errorFlag = true;
            lastnameError = true;
            request.setAttribute("lastnameError", lastnameError);
        }

        if (email == null
                || email.equals("")
                || !email.contains("@")
                || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errorFlag = true;
            emailError = true;
            request.setAttribute("emailError", emailError);
        }
        if (phone == null
                || phone.equals("")
                || phone.matches("(\\d-)?(\\d{3}-)?\\d{3}-\\d{4}")) {
            errorFlag = true;
            phoneError = true;
            request.setAttribute("phoneError", phoneError);
        }
        if (address == null
                || address.equals("")
                || address.length() > 130) {
            errorFlag = true;
            addressError = true;
            request.setAttribute("addressError", addressError);
        }

        if (password == null
                || password.equals("")
                || password.length() > 15) {
            errorFlag = true;
            passwordError = true;
            request.setAttribute("passwordError", passwordError);
        }

        return errorFlag;
    }
}
