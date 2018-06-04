/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Businesses;
import entity.Customers;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.AccountManager;
import session.AccountTypeFacade;
import session.BusinessesFacade;
import session.CustomersFacade;
import session.ServicesFacade;
import validator.Validator;

/**
 *
 * @author Stephane
 */
@WebServlet(name = "ControllerServlet",
        loadOnStartup = 1,
        urlPatterns = {"/banking", "/selecttype", "/services", "/login", "/logout", "/create"})
public class ControllerServlet extends HttpServlet {

    @EJB
    private BusinessesFacade businessesFacade;

    @EJB
    private CustomersFacade customersFacade;

    @EJB
    private AccountManager accountManager;

    @EJB
    private AccountTypeFacade accountTypeFacade;

    @EJB
    private ServicesFacade servicesFacade;

    private boolean isLogin = false;
    private List<Customers> customerList;
    private List<Businesses> businessesList;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.getServletContext().setAttribute("accountTypes", accountTypeFacade.findAll());
        this.getServletContext().setAttribute("bankServices", servicesFacade.findAll());
        customerList = customersFacade.findAll();
        businessesList = businessesFacade.findAll();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectPath = request.getServletPath();
        HttpSession session = request.getSession();
        customerList = customersFacade.findAll();
        businessesList = businessesFacade.findAll();
        if (projectPath.equals("/services")) {

            projectPath = "services";

        } else if (projectPath.equals("/selecttype")) {
            String accnttype = request.getParameter("acctype");

            if (accnttype.equals("customer")) {
                accnttype = "customer";
            } else {
                accnttype = "business";
            }

            request.setAttribute("acctype", accnttype);
            projectPath = "banking";

        } else if (projectPath.equals("/payment")) {
            projectPath = "payment";

        } else if (projectPath.equals("/banking")) {
            projectPath = "banking";

        } else if (projectPath.equals("/logout")) {
            session.invalidate();
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        } else {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

        String response_url = "/WEB-INF/" + projectPath + ".jsp";

        this.getServletContext().getRequestDispatcher(response_url).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectPath = request.getServletPath();
        HttpSession session = request.getSession();
        customerList = customersFacade.findAll();
        businessesList = businessesFacade.findAll();
        if (projectPath.equals("/create")) {
            String accounttyp = request.getParameter("accounttype");

            String fName = request.getParameter("fname");
            String lName = request.getParameter("lname");
            String email = request.getParameter("emailid");
            String phone = request.getParameter("phonenumber");
            String address = request.getParameter("address");

            String depos = request.getParameter("deposit");
            String password = request.getParameter("pssword");
            String confirm = request.getParameter("confirm");
            String acctyp = request.getParameter("accntype");
            int actype = Integer.parseInt(acctyp);
            double minimal = accountTypeFacade.find(actype).getMinimumDeporit();
            double deposit = Double.valueOf(depos);

            boolean validationErrorFlag = new Validator().validateRegisterForm(fName, lName, email, phone, address, password, confirm, request);
            if (deposit < minimal) {
                validationErrorFlag = true;
            }
            if (validationErrorFlag) {
                request.setAttribute("validationErrorFlag", validationErrorFlag);
                this.getServletContext().getRequestDispatcher("/WEB-INF/banking.jsp").forward(request, response);
                return;
            }
            int accountid = accountManager.createCustomerAccount(fName, lName, email, phone, address, password, actype, deposit, minimal, accounttyp);

            if (accountid != 0 && accounttyp.equals("customer")) {
                request.setAttribute("customerDetail", accountManager.getCustomerDetail(accountid));
                request.setAttribute("accountTypeDetail", accountManager.getAccountTypeDetail(accountid));
                request.setAttribute("accountDetail", accountManager.getAccountDetail(accountid));
                request.setAttribute("isCreated", "yeah");
                request.setAttribute("message", "Account successfully create: email : " + email + " password : " + password);

            } else if (accountid != 0 && accounttyp.equals("business")) {
                request.setAttribute("businessDetail", accountManager.getBusinessDetail(accountid));
                request.setAttribute("accountTypeDetail", accountManager.getAccountTypeDetail(accountid));
                request.setAttribute("accountDetail", accountManager.getAccountDetail(accountid));
                request.setAttribute("isCreated", "yeah");
                request.setAttribute("message", "Account successfully create: email : " + email + " password : " + password);
            }

            //projectPath = "/account/index";
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;

        } else if (projectPath.equals("/deposit")) {

        } else if (projectPath.equals("/login")) {
            String email = request.getParameter("emailid");
            String password = request.getParameter("pssword");

            Businesses businesses = null;
            Customers customers = null;
            for (Businesses business : businessesList) {
                if (business.getEmailid().equals(email) && business.getPassword().equals(password)) {
                    businesses = business;
                    break;
                }
            }
            for (Customers customer : customerList) {
                if (customer.getEmailId().equals(email) && customer.getPassword().equals(password)) {
                    customers = customer;
                    break;
                }
            }
            if (businesses != null) {
                isLogin = true;
                session.setAttribute("isLogin", isLogin);
                session.setAttribute("business", businesses);
                session.setAttribute("clientType", "B");
            }

            if (customers != null) {
                isLogin = true;
                session.setAttribute("isLogin", isLogin);
                session.setAttribute("customer", customers);
                session.setAttribute("clientType", "C");
            }

            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;

        } else {
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        String response_url = "/WEB-INF/" + projectPath + ".jsp";

        this.getServletContext().getRequestDispatcher(response_url).forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
