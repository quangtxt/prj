/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class AccountDAO extends DBContext{
    public Account getAccount(String email, String pass) {
        Account ac = null;
        try {
            String sql = "select * from Accounts where Email=? and Password=?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");  
                ac = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
            }
        } catch (SQLException e) {
        }
        return ac;
    }
    public Account getAccountByEmail(String email) {
        Account ac = null;
        try {
            String sql = "select * from Accounts where Email=?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");  
                ac = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
            }
        } catch (SQLException e) {
        }
        return ac;
    }
    public int SignUp(Customer cus, Account acc){
        int result1 = 0,result2 = 0;
        try {
        String sql ="insert into Customers values (?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cus.getCustomerID());
            st.setString(2, cus.getCompanyName());
            st.setString(3, cus.getContactName());
            st.setString(4, cus.getContactTitle());
            st.setString(5, cus.getAddress());
            result1=st.executeUpdate();
            String sql1 ="insert into Accounts(Email,Password,CustomerID,Role) values (?,?,?,?)";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, acc.getEmail());
            st1.setString(2, acc.getPassword());
            st1.setString(3, cus.getCustomerID());
            st1.setInt(4, acc.getRole());
            result2=st1.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (result1!=0&&result2!=0) {
            return 1;
        } else {
            return 0;
        }
    }
}
