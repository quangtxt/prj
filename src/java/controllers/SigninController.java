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
public class SigninController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("AccSession") != null) {
            req.getSession().removeAttribute("AccSession");
            req.getSession().removeAttribute("role");
            req.getSession().removeAttribute("uname");
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.getRequestDispatcher("/signin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("txtEmail");
        String password = req.getParameter("txtPassword");
        String msgEmail = "Email is required", msgPass = "Password is required";
        if (email.equals("")) {
            req.setAttribute("msgEmail", msgEmail);
        }
        if (password.equals("")) {
            req.setAttribute("msgPass", msgPass);
        }
        if (email.equals("") || password.equals("")) {
            req.getRequestDispatcher("../signin.jsp").forward(req, resp);
        } else {
            Account acc = new AccountDAO().getAccount(email, password);
            if (acc != null) {
                //cap session
                req.getSession().setAttribute("AccSession", acc);
                if (acc.getRole() == 1) {  
                    req.getSession().setAttribute("role", 1);
                    req.getSession().setAttribute("uname", new EmployeesDAO().getEmployeebyID(acc.getEmployeeID()).getFirstName());
                    resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
                } else {
                    req.getSession().setAttribute("uname", new CustomerDAO().getCustomer(acc.getCustomerID()).getContactName());
                    resp.sendRedirect(req.getContextPath() + "/home");
                }

            } else {
                req.setAttribute("msg", "This account does not exist.");
                req.getRequestDispatcher("../signin.jsp").forward(req, resp);
            }
        }
    }
}