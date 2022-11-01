/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.DBContext;
import dal.Employees;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class EmployeesDAO extends DBContext{
    public Employees getEmployeebyID(int emID) {
        Employees em = null;
        try {
            String sql = "select * from Employees where EmployeeID = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, emID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int EmployeeID = rs.getInt("EmployeeID");
                String LastName = rs.getString("LastName");
                String FirstName = rs.getString("FirstName");
                int DepartmentID = rs.getInt("DepartmentID");
                String Title = rs.getString("Title");
                String TitleOfCourtesy = rs.getString("TitleOfCourtesy");
                Date BirthDate = rs.getDate("BirthDate");
                Date HireDate = rs.getDate("HireDate");
                String Address = rs.getString("Address");
                em = new Employees(EmployeeID, LastName, FirstName, DepartmentID, Title, TitleOfCourtesy, BirthDate, HireDate, Address);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return em;
    }
    public ArrayList<Employees> getAllEmployee() {
        ArrayList<Employees> listEmp = new ArrayList<>();
        try {
            String sql = "select * from Employees ;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int EmployeeID = rs.getInt("EmployeeID");
                String LastName = rs.getString("LastName");
                String FirstName = rs.getString("FirstName");
                int DepartmentID = rs.getInt("DepartmentID");
                String Title = rs.getString("Title");
                String TitleOfCourtesy = rs.getString("TitleOfCourtesy");
                Date BirthDate = rs.getDate("BirthDate");
                Date HireDate = rs.getDate("HireDate");
                String Address = rs.getString("Address");
                listEmp.add(new Employees(EmployeeID, LastName, FirstName, DepartmentID, Title, TitleOfCourtesy, BirthDate, HireDate, Address));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listEmp;
    }
}
