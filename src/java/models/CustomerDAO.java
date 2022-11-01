/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.Customer;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author DELL
 */
public class CustomerDAO extends DBContext{
      public ArrayList<Customer> getALlCustomer(){
        ArrayList<Customer> list = new ArrayList<>();
        try {
            String sql = "select * from Customers;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                Customer cust = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address);
                list.add(cust);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    
    public Customer getCustomer(String custID) {
        Customer cust = null;
        try {
            String sql = "select * from Customers where CustomerID=?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, custID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                cust = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address);
                
            }
        } catch (SQLException e) {
        }
        return cust;
    }
    public String randomString(int n) {
        String str = "";
        for (int i = 0; i < n; i++) {
            str += (char) (new Random().nextInt(26) + 'A');
        }
        return str;
    }

    public String newCustomerID() {
        String CustomerID = randomString(5);
        ArrayList<Customer> listCus = new CustomerDAO().getALlCustomer();
        for (Customer item : listCus) {
            if (item.getCustomerID().equals(CustomerID)) {
                return newCustomerID();
            }
        }
        return CustomerID;
    }
    
    public int addCustomer(Customer cust) {
        int result = 0;
        try {
            String sql = "insert into Customers values (?,?,?,?,?);";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cust.getCustomerID());
            st.setString(2, cust.getCompanyName());
            st.setString(3, cust.getContactName());
            st.setString(4, cust.getContactTitle());
            st.setString(5, cust.getAddress());
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    public int update(Customer cust) {
        int result = 0;
        String sql = "update Customers  set CompanyName= ?,ContactName=?,ContactTitle=?,Address=? where CustomerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cust.getCompanyName());
            st.setString(2, cust.getContactName());
            st.setString(3, cust.getContactTitle());
            st.setString(4, cust.getAddress());
            st.setString(5, cust.getCustomerID());
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
