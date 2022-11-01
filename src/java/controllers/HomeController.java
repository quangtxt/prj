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
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", new CategoriesDAO().getCategories());
        req.setAttribute("products1", new ProductDAO().get4TopProducts());
        req.setAttribute("products2", new ProductDAO().get4SaleProducts());
        req.setAttribute("products3", new ProductDAO().get4NewProducts());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO pro = null;
        req.setAttribute("list", new CategoriesDAO().getCategories());
        if (req.getParameter("txtSearch").isEmpty()) {  
            req.setAttribute("products1", new ProductDAO().get4TopProducts());
            req.setAttribute("products2", new ProductDAO().get4SaleProducts());
            req.setAttribute("products3", new ProductDAO().get4NewProducts());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            String search = req.getParameter("txtSearch");
            pro = new ProductDAO(new ProductDAO().getProductsByCondition(1, "", search));
            pro.setRange(12);
            pro.setPage(req.getParameter("page")); 
            req.setAttribute("search", search);
            req.setAttribute("page", pro.getPage());
            req.setAttribute("endP", pro.getEndPage());
            req.setAttribute("listPani", pro.getProductPani(1, "", search));
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

}
