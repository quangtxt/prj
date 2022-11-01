/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dal.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import models.*;

/**
 *
 * @author DELL
 */
public class ProfileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account a = (Account) req.getSession().getAttribute("AccSession");
        Account acc = new AccountDAO().getAccountByEmail(a.getEmail());
        Customer cust = new CustomerDAO().getCustomer(acc.getCustomerID());
        req.setAttribute("acc", acc);
        if (req.getParameter("action") == null) {
            req.setAttribute("cust", cust);
            req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);
        } else {
            String CompanyName = req.getParameter("txtCompanyName");
            String ContactName = req.getParameter("txtContactName");
            String CompanyTitle = req.getParameter("txtCompanyTitle");
            String Address = req.getParameter("txtAddress");
            if (CompanyName.equals("") || ContactName.equals("") || CompanyTitle.equals("") || Address.equals("")) {
                req.setAttribute("err", "Do not leave any information blank");
                req.setAttribute("cust", cust);
                req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);
            } else {
                Customer c = new Customer(cust.getCustomerID(), CompanyName, ContactName, CompanyTitle, Address);
                new CustomerDAO().update(c);
                cust = new CustomerDAO().getCustomer(acc.getCustomerID());
                req.setAttribute("cust", cust);
                req.getRequestDispatcher("/profile.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account a = (Account) req.getSession().getAttribute("AccSession");
        if (a != null) {
            Account acc = new AccountDAO().getAccountByEmail(a.getEmail());
            Customer cust = new CustomerDAO().getCustomer(acc.getCustomerID());
            req.setAttribute("OrderDAO", new OrderDAO());
            req.setAttribute("ProductDAO", new ProductDAO());
            if (req.getParameter("order") == null) {
                req.setAttribute("acc", acc);
                req.setAttribute("cust", cust);
                req.getRequestDispatcher("/profile.jsp").forward(req, resp);
            } else if (req.getParameter("order").equals("list")) {
                req.setAttribute("oPending", new OrderDAO().getOrderByStatus(cust.getCustomerID(), 2));
                req.setAttribute("oComplete", new OrderDAO().getOrderByStatus(cust.getCustomerID(), 1));
                req.getRequestDispatcher("../orderCust.jsp").forward(req, resp);
            } else if (req.getParameter("order").equals("cancel")) {
                if (req.getParameter("ID") != null) {
                    new OrderDAO().cancelOrder(req.getParameter("ID"));
                }
                req.setAttribute("oCancel", new OrderDAO().getOrderByStatus(cust.getCustomerID(), 3));
                req.getRequestDispatcher("../orderCust.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/signin.jsp").forward(req, resp);
        }
    }
}
