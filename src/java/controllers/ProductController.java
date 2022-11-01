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
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            if (req.getParameter("cateID") == null) {
                resp.sendError(404);
            } else {
                String cateID = req.getParameter("cateID");
                ArrayList<Product> proList = (new ProductDAO()).getProductsByCondition(2, cateID, "");
                req.setAttribute("list", new CategoriesDAO().getCategories());
                req.setAttribute("products", proList);
                Categories cate = new CategoriesDAO().getCategory(Integer.parseInt(cateID));
                req.setAttribute("ca", cate);
                req.getRequestDispatcher("productsCate.jsp").forward(req, resp);
            }
        } else {
            switch (action) {
                case "create":
                    ArrayList<Categories> cateList1 = new CategoriesDAO().getCategories();
                    req.setAttribute("cateLists", cateList1);
                    req.getRequestDispatcher("/create-product.jsp").forward(req, resp);
                    break;
                case "list":
                    ProductDAO pro = null;
                    pro = new ProductDAO(new ProductDAO().getProductsByCondition(0, "", ""));
                    pro.setRange(10);
                    pro.setPage(req.getParameter("page"));
                    ArrayList<Categories> cateList2 = new CategoriesDAO().getCategories();
                    req.setAttribute("page", pro.getPage());
                    req.setAttribute("endP", pro.getEndPage());
                    req.setAttribute("cateLists", cateList2);
                    req.setAttribute("proLists", pro.getProductPani(0, "", ""));
                    req.getRequestDispatcher("/product.jsp").forward(req, resp);
                    break;
                case "delete":
                    int ID = Integer.parseInt(req.getParameter("ID"));
                    if (new OrderDAO().CheckProIDInOrderDetail(ID)) {
                        req.setAttribute("msg", "Product is in order detail so can not delete");
                        req.getRequestDispatcher("product?action=list").forward(req, resp);
                    } else {
                        new ProductDAO().delete(ID);
                        resp.sendRedirect("product?action=list");
                    }
                    break;
                case "edit":
                    int ID1 = Integer.parseInt(req.getParameter("ID"));
                    req.setAttribute("p", new ProductDAO().getProductsByID(ID1));
                    ArrayList<Categories> ca = new CategoriesDAO().getCategories();
                    req.setAttribute("cate", ca);
                    req.getRequestDispatcher("/editProduct.jsp").forward(req, resp);
                    break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("create")) {
            String ProductName = req.getParameter("txtProductName");
            int CategoryID = Integer.parseInt(req.getParameter("txtCategoryID"));
            String QuantityPerUnit = req.getParameter("txtQuantityPerUnit");
            double UnitPrice = Double.parseDouble(req.getParameter("txtUnitPrice"));
            int UnitsInStock = Integer.parseInt(req.getParameter("txtUnitsInStock"));
            int ReorderLevel = Integer.parseInt(req.getParameter("txtReorderLevel"));
            boolean Discontinued = Boolean.parseBoolean(req.getParameter("txtDiscontinued"));
            boolean flag = false;
            if (ProductName.equals("")) {
                req.setAttribute("msgProductName", "ProductName is required");
                flag = true;
            }
            if (QuantityPerUnit.equals("")) {
                req.setAttribute("msgQuantityPerUnit", "QuantityPerUnit is required");
                flag = true;
            }
            if (flag) {
                req.getRequestDispatcher("../crateNewProduct.jsp").forward(req, resp);
            } else {
                Product p = new Product(0, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, 0, ReorderLevel, Discontinued);
                new ProductDAO().insert(p);
                resp.sendRedirect("product?action=list");
            }
        } else if (req.getParameter("action").equals("edit")) {
            try {
                int ID = Integer.parseInt(req.getParameter("ID"));
                String ProductName = req.getParameter("txtProductName");
                int CategoryID = Integer.parseInt(req.getParameter("txtCategoryID"));
                String QuantityPerUnit = req.getParameter("txtQuantityPerUnit");
                double UnitPrice = Double.parseDouble(req.getParameter("txtUnitPrice"));
                int UnitsInStock = Integer.parseInt(req.getParameter("txtUnitsInStock"));
                int UnitsOnOrder = Integer.parseInt(req.getParameter("txtUnitsOnOrder"));
                int ReorderLevel = Integer.parseInt(req.getParameter("txtReorderLevel"));
                boolean Discontinued = Boolean.parseBoolean(req.getParameter("txtDiscontinued"));
                Product p = new Product(ID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                new ProductDAO().update(p);
                resp.sendRedirect("product?action=list");

            } catch (Exception e) {
                req.setAttribute("err", "Do not leave any information blank");
                int ID1 = Integer.parseInt(req.getParameter("ID"));
                req.setAttribute("p", new ProductDAO().getProductsByID(ID1));
                ArrayList<Categories> ca = new CategoriesDAO().getCategories();
                req.setAttribute("cate", ca);
                req.getRequestDispatcher("/editProduct.jsp").forward(req, resp);
            }

        } else if (req.getParameter("action").equals("list")) {
            ProductDAO pro = null;
            if (req.getParameter("txtCategoryID") != null && req.getParameter("txtSearch") != null) {
                req.setAttribute("categoryID", req.getParameter("txtCategoryID"));
                req.setAttribute("search", req.getParameter("txtSearch"));
                pro = new ProductDAO(new ProductDAO().getProductsByCondition(3, req.getParameter("txtCategoryID"), req.getParameter("txtSearch")));
                pro.setRange(10);
                pro.setPage(req.getParameter("page"));
                req.setAttribute("proLists", pro.getProductPani(3, req.getParameter("txtCategoryID"), req.getParameter("txtSearch")));
            } else if (req.getParameter("txtCategoryID") != null) {
                req.setAttribute("categoryID", req.getParameter("txtCategoryID"));
                pro = new ProductDAO(new ProductDAO().getProductsByCondition(2, req.getParameter("txtCategoryID"), ""));
                pro.setRange(10);
                pro.setPage(req.getParameter("page"));
                req.setAttribute("proLists", pro.getProductPani(2, req.getParameter("txtCategoryID"), ""));
            } else {
                String search = req.getParameter("txtSearch");
                req.setAttribute("search", search);
                pro = new ProductDAO(new ProductDAO().getProductsByCondition(1, "", search));
                pro.setRange(10);
                pro.setPage(req.getParameter("page"));
                req.setAttribute("proLists", pro.getProductPani(1, "", search));
            }
            ArrayList<Categories> cateList2 = new CategoriesDAO().getCategories();
            req.setAttribute("page", pro.getPage());
            req.setAttribute("endP", pro.getEndPage());
            req.setAttribute("cateLists", cateList2);
            req.getRequestDispatcher("/product.jsp").forward(req, resp);
        }
    }
}
