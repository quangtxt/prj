/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class OrderDAO extends Paging {
    ArrayList<Orders> list;

    public OrderDAO() {
    }

    public OrderDAO(ArrayList<Orders> list) {
        this.list = list;
        super.setSize(list.size()); 
    }
    
    
    public ArrayList<Orders> getOrderByStatus(String custID, int status) {
        ArrayList<Orders> listOrder = new ArrayList<>();
        try {
            String str;
            switch (status) {
                case 1:
                    str = "and ShippedDate is not null and RequiredDate is not null";
                    break;
                case 2:
                    str = "and ShippedDate is null and RequiredDate is not null";
                    break;
                case 3:
                    str = "and RequiredDate is null";
                    break;
                default:
                    str = "";
            }
            String sql = "select * from Orders where CustomerID = '" + custID + "' "+ str +" order by OrderDate desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");
                Orders ord = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                listOrder.add(ord);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }

    public ArrayList<OrderDetails> getOrderDetails(int orderID) {
        ArrayList<OrderDetails> listOrder = new ArrayList<>();
        try {
            String sql = "select * from [Order Details] where OrderID = " + orderID + ";";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                OrderDetails ordetail = new OrderDetails(OrderID, ProductID, UnitPrice, Quantity, Discount);
                listOrder.add(ordetail);
            }
        } catch (SQLException e) {
        }
        return listOrder;
    }

    public ArrayList<Orders> getAllOrder() {
        ArrayList<Orders> listOrder = new ArrayList<>();
        try {
            String sql = "select * from Orders;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");
                Orders ord = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                listOrder.add(ord);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }
    public ArrayList<Orders> getOrderfilter(Date begin,Date end) {
        ArrayList<Orders> listOrder = new ArrayList<>();
        try {
            String sql ="select * from orders where OrderDate between ? and ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, begin);
            ps.setDate(2, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");
                Orders ord = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                listOrder.add(ord);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }

    public boolean CheckProIDInOrderDetail(int proID) {

        try {
            String sql = "  select ProductID from [Order Details] group by ProductID;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                if (proID == ProductID) {
                    return true;
                }
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public ArrayList<Orders> getOrdersPani() {
        ArrayList<Orders> Orders = new ArrayList<>();
        try {
            String sql = "  select * from Orders order by OrderId  desc offset " +((getPage() - 1) * getRange()) + " rows fetch next " + getRange() + " rows only;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");
                Orders ord = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                Orders.add(ord);
            }
        } catch (SQLException e) {
        }
        return Orders;
    }
    public ArrayList<Orders> getOrdersPaniFilter(Date begin,Date end) {
        ArrayList<Orders> Orders = new ArrayList<>();
        try {
            String sql = "  select * from Orders where OrderDate between ? and ? order by OrderId  desc offset " +((getPage() - 1) * getRange()) + " rows fetch next " + getRange() + " rows only;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, begin);
            ps.setDate(2, end);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");
                Orders ord = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                Orders.add(ord);
            }
        } catch (SQLException e) {
        }
        return Orders;
    }

    public int addOrder(Orders order) {
        int result = 0;
        try {
            String sql = "insert into Orders(CustomerID,EmployeeID,OrderDate,RequiredDate) values (?,?,?,?);";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, order.getCustomerID());
            st.setInt(2, 2);
            st.setDate(3, order.getOrderDate());
            st.setDate(4, order.getRequiredDate());
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public int addOrderDetail(OrderDetails ode) {
        int result = 0;
        try {
            String sql = "insert into [Order Details] values ((select MAX(OrderID)'maxID' from Orders),?,?,?,?);";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ode.getProductID());
            st.setDouble(2, ode.getUnitPrice());
            st.setInt(3, ode.getQuantity());
            st.setDouble(4, ode.getDiscount());
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public void cancelOrder(String ID) {
        try {
            String sql = "update Orders set RequiredDate = null where OrderID = " + ID + ";";
            PreparedStatement st = connection.prepareStatement(sql);
            st.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
