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
import java.util.ArrayList;
import models.*;

/**
 *
 * @author DELL
 */
public class OrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date sDate,eDate;
        if (req.getParameter("txtStartOrderDate").equals("")) {
             sDate = new Date(0, 0, 0);
        } else {
            sDate = Date.valueOf(req.getParameter("txtStartOrderDate"));
        }
        if (req.getParameter("txtEndOrderDate").equals("")) {
             eDate = Date.valueOf(LocalDate.now());
        } else {
            eDate = Date.valueOf(req.getParameter("txtEndOrderDate"));
        }
        OrderDAO ord = new OrderDAO(new OrderDAO().getOrderfilter(sDate, eDate));
        ord.setRange(10);
        ord.setPage(req.getParameter("page"));
        req.setAttribute("sDate", sDate);
        req.setAttribute("eDate", eDate);
        req.setAttribute("page", ord.getPage());
        req.setAttribute("endP", ord.getEndPage());
        req.setAttribute("listOrd", ord.getOrdersPaniFilter(sDate, eDate));
        req.setAttribute("listCus", new CustomerDAO().getALlCustomer());
        req.setAttribute("listEmp", new EmployeesDAO().getAllEmployee());
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") != null) {
            new OrderDAO().cancelOrder(req.getParameter("ID"));
        }

        OrderDAO ord = new OrderDAO(new OrderDAO().getAllOrder());
        ord.setRange(10);
        ord.setPage(req.getParameter("page"));
        req.setAttribute("page", ord.getPage());
        req.setAttribute("endP", ord.getEndPage());
        req.setAttribute("listOrd", ord.getOrdersPani());
        req.setAttribute("listCus", new CustomerDAO().getALlCustomer());
        req.setAttribute("listEmp", new EmployeesDAO().getAllEmployee());
        req.getRequestDispatcher("/order.jsp").forward(req, resp);
    }

}
