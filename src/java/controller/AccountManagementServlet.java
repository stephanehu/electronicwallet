/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.AccountDocuments;
import entity.AccountOwner;
import entity.Accounts;
import entity.Businesses;
import entity.Customers;
import entity.Services;
import entity.Transactions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.AccountManager;
import session.AccountOwnerFacade;
import session.AccountsFacade;
import session.BusinessesFacade;
import session.CustomersFacade;
import session.TransactionManager;
import session.TransactionsFacade;

/**
 *
 * @author Stephane Ehu Alias RigtheousByGod 
 */
@WebServlet(name = "AccountManagementServlet",
        loadOnStartup = 1,
        urlPatterns = {
            "/account/",
            "/account/selectbusiness",
            "/account/viewProfile",
            "/account/transact",
            "/account/updateCustomer",
            "/account/deposit",
            "/account/viewAccount",
            "/account/viewDocument",
            "/account/historic",
            "/account/orderRecord",
            "/account/logout"})
public class AccountManagementServlet extends HttpServlet {

    @EJB
    private AccountManager accountManager;

    @EJB
    private TransactionManager transactionManager;

    @EJB
    private TransactionsFacade transactionsFacade;

    @EJB
    private BusinessesFacade businessesFacade;

    @EJB
    private AccountsFacade accountsFacade;

    @EJB
    private AccountOwnerFacade accountOwnerFacade;

    @EJB
    private CustomersFacade customersFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String userPath = request.getServletPath();

        // if viewCustomers is requested
        if (userPath.equals("/account/viewProfile")) {
            String customerId = request.getQueryString();

            String clientType = (String) session.getAttribute("clientType");
            if (clientType != null && clientType.equals("C")) {
                Customers customer = null;
                try {
                    customer = customersFacade.find(Integer.parseInt(customerId));
                } catch (Exception ex) {

                }

                session.setAttribute("customer", customer);
                request.setAttribute("userPath", "customer");
            } else if (clientType != null && clientType.equals("B")) {
                Businesses business = null;
                try {
                    business = businessesFacade.find(Integer.parseInt(customerId));
                } catch (Exception ex) {

                }

                session.setAttribute("business", business);
                request.setAttribute("userPath", "business");
            }

            // if viewOrders is requested
        }

        if (userPath.equals(
                "/account/viewAccount")) {
            String customerId = request.getQueryString();
            Businesses business = null;
            Customers customer = null;
            AccountOwner ownerId = null;
            String clientType = (String) session.getAttribute("clientType");
            System.out.println(clientType);
            if (clientType.equals("C")) {

                try {
                    customer = customersFacade.find(Integer.parseInt(customerId));
                } catch (Exception ex) {

                }

            } else if (clientType.equals("B")) {

                try {
                    business = businessesFacade.find(Integer.parseInt(customerId));
                } catch (Exception ex) {

                }

            }

            if (customer != null) {
                ownerId = customer.getAccountOwner();
            }

            if (business != null) {
                ownerId = business.getAccountOwner();

            }

            Accounts account = null;
            Services service = null;
            Collection<Businesses> businessList = new ArrayList<>();
            try {
                if (ownerId == null) {
                    ownerId = accountOwnerFacade.findAll().get(accountOwnerFacade.findAll().size() - 1);
                }
                account = accountsFacade.findByOwnerId(ownerId);

                service = account.getTypeId().getServiceId();
                boolean elecBill = service.getEElectricitybilling();
                boolean waterBill = service.getEWaterbilling();
                boolean eRecharge = service.getERecharge();

                if (elecBill || waterBill) {
                    businessList.addAll(businessesFacade.findByBusinessType("E-Billing"));
                }
                if (eRecharge) {
                    businessList.addAll(businessesFacade.findByBusinessType("E-Recharge"));
                }
                if (customer != null) {
                    List<Transactions> transactionList = transactionsFacade.findByCustomerId(ownerId);
                    session.setAttribute("transactions", transactionList);
                }
                if (business != null) {
                    List<Transactions> transactionList = transactionsFacade.findByBusinessId(ownerId);
                    session.setAttribute("transactions", transactionList);
                }

            } catch (Exception e) {

            }

            session.setAttribute("businessList", businessList);
            session.setAttribute("accountDetails", account);
            //request.setAttribute("serviceDetails", services);
        }

        //if the business is selected
        if (userPath.equals(
                "/account/selectbusiness")) {
            String businessId = request.getParameter("busname");
            request.setAttribute("businessId", businessId);
        }
        if (userPath.equals(
                "/account/historic")) {
            AccountOwner ownerId = null;
            String businessId = request.getParameter("busname");

            try {
                Businesses busin = businessesFacade.find(Integer.parseInt(businessId));
                System.out.println(busin.toString());

                ownerId = busin.getAccountOwner();
                List<Transactions> transactionList = transactionsFacade.findByBusinessId(ownerId);
                session.setAttribute("transactions", transactionList);

            } catch (Exception ex) {

            }

            request.setAttribute("businessId", businessId);
        }
        if (userPath.equals(
                "/account/transact")) {
            String businessId = request.getParameter("busid");
            String customerId = request.getParameter("custid");
            System.out.println("cust id: "+customerId);
            String number = request.getParameter("number");
            String amount = request.getParameter("amount");

            String message = "Transaction failed";
            int transactionId = 0;
            try {
                transactionId = transactionManager.transact(number, amount, customerId, businessId);

            } catch (Exception e) {
                message = "Due to internalreasons";
            }
            if (transactionId != -1) {
                message = "Transaction Successfull!";
                Customers customers = customersFacade.find(Integer.parseInt(customerId));
                AccountOwner ownerId = customers.getAccountOwner();
                Accounts customerAccount = null;
                List<Transactions> transactionList = null;
                if (ownerId == null) {
                    ownerId = accountOwnerFacade.findAll().get(accountOwnerFacade.findAll().size() - 1);
                    customerAccount = accountsFacade.findByOwnerId(ownerId);
                    System.out.println("ici "+ownerId);
                    transactionList = transactionsFacade.findByCustomerId(ownerId);
                } else {
                    customerAccount = accountsFacade.findByOwnerId(customers.getAccountOwner());
                    System.out.println("la");
                    transactionList = transactionsFacade.findByCustomerId(customers.getAccountOwner());
                }
                session.setAttribute("transactions", transactionList);
                session.setAttribute("accountDetails", customerAccount);
            }

            request.setAttribute("message", message);
        }

        // if customerRecord is requested
        if (userPath.equals(
                "/account/viewDocument")) {

            // get customer ID from request
            String customerId = request.getQueryString();
            Businesses business = null;
            Customers customer = null;
            AccountDocuments documents = null;
            String clientType = (String) session.getAttribute("clientType");
            if (clientType.equals("C")) {

                try {
                    customer = customersFacade.find(Integer.parseInt(customerId));
                    documents = customer.getAccountOwner().getAccountDocuments();
                    request.setAttribute("userPath", "customer");
                } catch (Exception ex) {

                }

            } else if (clientType.equals("B")) {

                try {
                    business = businessesFacade.find(Integer.parseInt(customerId));
                    documents = business.getAccountOwner().getAccountDocuments();
                    request.setAttribute("userPath", "business");
                } catch (Exception ex) {

                }

            }

            request.setAttribute("customerDocument", documents);

        } else if (userPath.equals("/account/updateCustomer")) {
            String accounttyp = request.getParameter("accountType");
            String id = request.getParameter("clientid");
            int clientid = Integer.valueOf(id);
            String fName = request.getParameter("fname");
            String lName = request.getParameter("lname");
            String email = request.getParameter("emailid");
            String phone = request.getParameter("phonenumber");
            String address = request.getParameter("address");
            String password = request.getParameter("pssword");

            if (accounttyp.equals("customer")) {
                Customers customer = customersFacade.find(clientid);
                customer.setFirstname(fName);
                customer.setLastname(lName);
                customer.setPhone(phone);
                customer.setEmailId(email);
                customer.setAddress(address);
                customer.setPassword(password);
                customersFacade.edit(customer);
                //request.setAttribute("customerDetail", accountManager.getCustomerDetail(clientid));

            } else if (accounttyp.equals("business")) {
                Businesses business = businessesFacade.find(clientid);
                business.setBusinessName(fName);
                business.setBusinessType(lName);
                business.setPhonenumber(phone);
                business.setEmailid(email);
                business.setAddress(address);
                business.setPassword(password);
                businessesFacade.edit(business);
                //request.setAttribute("businessDetail", accountManager.getBusinessDetail(clientid));
            }

        } else if (userPath.equals("/account/deposit")) {
            String depos = request.getParameter("deposit");
            String accId = request.getParameter("accountId");
            double deposit = Double.valueOf(depos);
            int accountId = Integer.valueOf(accId);

            if (deposit < 0) {
                deposit = 0;
            }
            accountManager.deposit(accountId, deposit);

            //projectPath = "index";
        }

        // if orderRecord is requested
        if (userPath.equals(
                "/account/orderRecord")) {

            // get customer ID from request
            String orderId = request.getQueryString();

            // get order details
            //Map orderMap = orderManager.getOrderDetails(Integer.parseInt(orderId));
            // place order details in request scope
//            request.setAttribute("customer", orderMap.get("customer"));
//            request.setAttribute("products", orderMap.get("products"));
//            request.setAttribute("orderRecord", orderMap.get("orderRecord"));
//            request.setAttribute("orderedProducts", orderMap.get("orderedProducts"));
        }
        if (userPath.equals(
                "/account/logout")) {
            session = request.getSession();
            session.invalidate();
            return;
        }

        // use RequestDispatcher to forward request internally
        userPath = "/account/index.jsp";

        try {
            request.getRequestDispatcher(userPath).forward(request, response);
        } catch (ServletException | IOException ex) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
