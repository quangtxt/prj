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
import java.util.ArrayList;
import models.*;

/**
 *
 * @author DELL
 */
public class PaniController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("prv") == null) {
            ProductDAO pro = null;
            if (req.getParameter("txtCategoryID") != null) {
                req.setAttribute("categoryID", req.getParameter("txtCategoryID"));
                pro = new ProductDAO(new ProductDAO().getProductsByCondition(2, req.getParameter("txtCategoryID"), ""));
                pro.setRange(10);
                pro.setPage(req.getParameter("page"));
                req.setAttribute("proLists", pro.getProductPani(2, req.getParameter("txtCategoryID"), ""));
            } else if (req.getParameter("txtSearch") != null) {
                String search = req.getParameter("txtSearch");
                req.setAttribute("search", search);
                pro = new ProductDAO(new ProductDAO().getProductsByCondition(1, "", search));
                pro.setRange(10);
                pro.setPage(req.getParameter("page"));
                req.setAttribute("proLists", pro.getProductPani(1, "", search));
            } else {
                pro = new ProductDAO(new ProductDAO().getProductsByCondition(0, "", ""));
                pro.setRange(10);
                pro.setPage(req.getParameter("page"));
                req.setAttribute("proLists", pro.getProductPani(0, "", ""));
            }
            ArrayList<Categories> cateList2 = new CategoriesDAO().getCategories();
            req.setAttribute("page", pro.getPage());
            req.setAttribute("endP", pro.getEndPage());
            req.setAttribute("cateLists", cateList2);
            req.getRequestDispatcher("/product.jsp").forward(req, resp);
        } else if (req.getParameter("prv").equals("order")) {
            OrderDAO ord;
            if (req.getParameter("sDate") == null) {
                ord = new OrderDAO(new OrderDAO().getAllOrder());
            } else {
                ord = new OrderDAO(new OrderDAO().getOrderfilter(Date.valueOf(req.getParameter("sDate")), Date.valueOf(req.getParameter("eDate"))));
                req.setAttribute("sDate", req.getParameter("sDate"));
                req.setAttribute("eDate", req.getParameter("eDate"));
            }
            ord.setRange(10);
            ord.setPage(req.getParameter("page"));
            req.setAttribute("page", ord.getPage());
            req.setAttribute("endP", ord.getEndPage());
            if (req.getParameter("sDate") == null) {
                req.setAttribute("listOrd", ord.getOrdersPani());
            } else {
                req.setAttribute("listOrd", ord.getOrdersPaniFilter(Date.valueOf(req.getParameter("sDate")), Date.valueOf(req.getParameter("eDate"))));
            }
            req.setAttribute("listCus", new CustomerDAO().getALlCustomer());
            req.setAttribute("listEmp", new EmployeesDAO().getAllEmployee());
            req.getRequestDispatcher("/order.jsp").forward(req, resp);
        } else {
            String search = req.getParameter("txtSearch");
            ProductDAO pro = new ProductDAO(new ProductDAO().getProductsByCondition(1, "", search));
            pro.setRange(12);
            pro.setPage(req.getParameter("page"));
            req.setAttribute("search", search);
            req.setAttribute("page", pro.getPage());
            req.setAttribute("endP", pro.getEndPage());
            req.setAttribute("listPani", pro.getProductPani(1, "", search));
            req.getRequestDispatcher("home").forward(req, resp);
        }
    }

}
