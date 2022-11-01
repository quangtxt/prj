/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.Categories;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class CategoriesDAO extends DBContext {

    public ArrayList<Categories> getCategories() {
        ArrayList<Categories> categories = new ArrayList<>();
        try {
            String sql = "select * from Categories";
//            b2
            PreparedStatement ps = connection.prepareStatement(sql);
            //b3
            ResultSet rs = ps.executeQuery();
            //b4 xuly kq
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description= rs.getString("Description");
                String Picture= rs.getString("Picture");
                Categories ca = new Categories(CategoryID, CategoryName, Description, Picture);
                categories.add(ca);
            }
        } catch (SQLException e) {
        }
        return categories;
    }
    public Categories getCategory(int cateID) {
        Categories ca = null;
        try {
            String sql = "select * from Categories where CategoryID = " + cateID + ";";
//            b2
            PreparedStatement ps = connection.prepareStatement(sql);
            //b3
            ResultSet rs = ps.executeQuery();
            //b4 xuly kq
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description= rs.getString("Description");
                String Picture= rs.getString("Picture");
                ca = new Categories(CategoryID, CategoryName, Description, Picture);
            }
        } catch (SQLException e) {
        }
        return ca;
    }
}
