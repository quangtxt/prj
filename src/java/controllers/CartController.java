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
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import models.*;

/**
 *
 * @author DELL
 */
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("ID") == null) {
            Account a = (Account) req.getSession().getAttribute("AccSession");
            req.setAttribute("productDAO", new ProductDAO());
            if (a != null) {
                Account acc = new AccountDAO().getAccountByEmail(a.getEmail());
                Customer cust = new CustomerDAO().getCustomer(acc.getCustomerID());
                req.setAttribute("cust", cust);
                req.getRequestDispatcher("/cart.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/cart.jsp").forward(req, resp);
            }
        } else {
            Map<Integer, Integer> cart = req.getSession().getAttribute("cart") != null ? (Map<Integer, Integer>) req.getSession().getAttribute("cart") : new HashMap<>();
            int ID = 0;
            try {
                ID = Integer.parseInt(req.getParameter("ID"));
            } catch (NumberFormatException e) {
            }
            if (req.getParameter("action") == null) {
                for (Map.Entry m : cart.entrySet()) {
                    if ((Integer) m.getKey() == ID) {
                        m.setValue((Integer) m.getValue() + 1);
                    }
                }
                if (!cart.containsKey(ID)) {
                    cart.put(ID, 1);
                }
            } else if (req.getParameter("action").equals("remove")) {
                cart.remove(ID);
            } else {
                for (Map.Entry m : cart.entrySet()) {
                    if ((Integer) m.getKey() == ID) {
                        if (req.getParameter("action").equals("add")) {
                            m.setValue((Integer) m.getValue() + 1);
                        } else if (req.getParameter("action").equals("sub")) {
                            m.setValue((Integer) m.getValue() - 1);
                        }
                    }
                }
            }

            req.getSession().setAttribute("cart", cart);
            Account a = (Account) req.getSession().getAttribute("AccSession");
            req.setAttribute("productDAO", new ProductDAO());
            if (a != null) {
                Account acc = new AccountDAO().getAccountByEmail(a.getEmail());
                Customer cust = new CustomerDAO().getCustomer(acc.getCustomerID());
                req.setAttribute("cust", cust);
                req.getRequestDispatcher("/cart.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/cart.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account a = (Account) req.getSession().getAttribute("AccSession");
        Customer cust = null;
        if (req.getParameter("txtRequiredDate").isEmpty() && a == null) {
            req.setAttribute("msg", "You have not entered required date");
            req.getRequestDispatcher("/cart.jsp").forward(req, resp);
        } else if (req.getParameter("txtRequiredDate").isEmpty() && a != null) {
            Account acc = new AccountDAO().getAccountByEmail(a.getEmail());
            cust = new CustomerDAO().getCustomer(acc.getCustomerID());
            req.setAttribute("cust", cust);
            req.setAttribute("msg", "You have not entered required date");
            req.getRequestDispatcher("/cart.jsp").forward(req, resp);
        } else {
            if (Date.valueOf(req.getParameter("txtRequiredDate")).before(Date.valueOf(LocalDate.now()))) {
                if (a != null) {
                    Account acc = new AccountDAO().getAccountByEmail(a.getEmail());
                    cust = new CustomerDAO().getCustomer(acc.getCustomerID());
                    req.setAttribute("cust", cust);
                }
                req.setAttribute("msg", "RequiredDate must be after current time now");
                req.getRequestDispatcher("/cart.jsp").forward(req, resp);
            } else {
                Map<Integer, Integer> cart = (Map<Integer, Integer>) req.getSession().getAttribute("cart");
                if (cart == null) {
                    if (a != null) {
                        Account acc = new AccountDAO().getAccountByEmail(a.getEmail());
                        cust = new CustomerDAO().getCustomer(acc.getCustomerID());
                        req.setAttribute("cust", cust);
                    }
                    req.setAttribute("msg", "No product to order");
                    req.getRequestDispatcher("/cart.jsp").forward(req, resp);
                } else {
                    if (a != null) {
                        Account acc = new AccountDAO().getAccountByEmail(a.getEmail());
                        cust = new CustomerDAO().getCustomer(acc.getCustomerID());
                        req.setAttribute("cust", cust);
                    } else {
                        String CompanyName = req.getParameter("txtCompanyName");
                        String ContactName = req.getParameter("txtContactName");
                        String ContactTitle = req.getParameter("txtContactTitle");
                        String Address = req.getParameter("txtAddress");
                        if (CompanyName == null || ContactName == null || ContactTitle == null || Address == null) {
                            req.setAttribute("msgError", "You must not leave any information blank");
                        } else {
                            cust = new Customer(new CustomerDAO().newCustomerID(), CompanyName, ContactName, ContactTitle, Address);
                            new CustomerDAO().addCustomer(cust);
                        }
                    }
                    Orders order = new Orders(0, cust.getCustomerID(), 0, Date.valueOf(LocalDate.now()),
                            Date.valueOf(req.getParameter("txtRequiredDate")),
                            null, 0, "", "", "", "", "", "");
                    int rs1 = new OrderDAO().addOrder(order);
                    int rs2 = 0;
                    for (Map.Entry m : cart.entrySet()) {
                        Product p = new ProductDAO().getProductsByID((Integer)m.getKey());
                        OrderDetails odetail = new OrderDetails(0, p.getProductID(), p.getUnitPrice(),(Integer)m.getValue(), 0);
                        rs2 = new OrderDAO().addOrderDetail(odetail);
                    }
                    if (rs1 > 0 && rs2 > 0) {
                        req.setAttribute("msgSuccessfull", "Order succesfull!");
                        req.getSession().removeAttribute("cart");
                    }
                    req.getRequestDispatcher("/cart.jsp").forward(req, resp);
                }
            }
        }

    }
}
