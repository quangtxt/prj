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
public class DetailController extends  HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ID = req.getParameter("ID");
        if (ID == null) {
            req.getRequestDispatcher("./home").forward(req, resp);
        } else {
            Product p = new ProductDAO().getProductsByID(Integer.parseInt(ID));
            req.setAttribute("p", p);
            Categories ca = new CategoriesDAO().getCategory(p.getCategoryID());
            req.setAttribute("ca", ca);
            req.getRequestDispatcher("../detail.jsp").forward(req, resp);
        }
    }
    
}
