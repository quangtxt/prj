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
import models.*;

/**
 *
 * @author DELL
 */
public class SignupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") == null) {
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("../index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String CompanyName = req.getParameter("txtCompanyName");
        String ContactName = req.getParameter("txtContactName");
        String ContactTitle = req.getParameter("txtContactTitle");
        String Address = req.getParameter("txtAddress");
        String email = req.getParameter("txtEmail");
        String password = req.getParameter("txtPassword");
        String rePass = req.getParameter("txtRe-Password");
        boolean flag = false;
        if (CompanyName.equals("")) {
            req.setAttribute("msgCompanyName", "CompanyName is required");
            flag = true;
        }
        if (ContactName.equals("")) {
            req.setAttribute("msgContactName", "ContactName is required");
            flag = true;
        }
        if (!email.matches("[a-zA-Z]\\w+@\\w+(\\.\\w+){1,3}")) {
            req.setAttribute("msgEmail", "Email is invalid. Enter email example: Quangtxt@gmail.com");
            flag = true;
        }
        if (password.equals("")) {
            req.setAttribute("msgPass", "Password is required");
            flag = true;
        }
        if (!password.equals(rePass)) {
            req.setAttribute("msgRePass", "Re-Password and password must be the same");
            flag = true;
        }
        if (flag) {
            req.getRequestDispatcher("../signup.jsp").forward(req, resp);
        } else {
            if (new AccountDAO().getAccountByEmail(email) != null) {
                req.setAttribute("msgEmail", "This email was existed.");
                req.getRequestDispatcher("../signup.jsp").forward(req, resp);

            } else {
                String CustomerID = new CustomerDAO().newCustomerID();
                Customer cus = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address);
                Account acc = new Account(email, password, CustomerID, 2);
                if (new AccountDAO().SignUp(cus, acc) > 0) {
                    req.getRequestDispatcher("../signin.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("../signup.jsp").forward(req, resp);
                }
            }
        }
    }
}
