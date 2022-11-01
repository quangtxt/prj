package models;

import dal.DBContext;
import dal.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ProductDAO extends Paging{

    ArrayList<Product> list;
    
    public ProductDAO() {
    }

    public ProductDAO(ArrayList<Product> list) {
        this.list = list;
        super.setSize(list.size()); 
    }

    public ArrayList<Product> getProductsByCondition(int otp,String cateID,String search) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String str;
            switch (otp) {
                case 1:
                    str = "where ProductName like '%" + search + "%'";
                    break;
                case 2:
                    str = "where CategoryID = " + cateID;
                    break;
                case 3:
                    str = "where CategoryID = "+cateID+" and where ProductName like '%"+search+"%'";
                    break;
                default:
                    str = "";
            }
            String sql = "select * from Products "+ str;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }
    public ArrayList<Product> getProductPani(int otp, String cateID, String search) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String str;
            switch (otp) {
                case 1:
                    str = "where ProductName like '%" + search + "%'";
                    break;
                case 2:
                    str = "where CategoryID = " + cateID;
                    break;
                case 3:
                    str = "where CategoryID = 2 and where ProductName like '%a%'";
                    break;
                default:
                    str = "";
            }
            String sql = "select * from Products " + str + " order by ProductID  desc offset " + ((getPage() - 1) * getRange()) + " rows fetch next " + getRange() + " rows only;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }
    public ArrayList<Product> get4TopProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "  select * from Products where ProductID in (select top 4 ProductID from [Order Details] order by Discount desc)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public ArrayList<Product> get4SaleProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "  select * from Products where ProductID in \n"
                    + "  (select top 4 [ProductID] from [Order Details] group by ProductID order by COUNT(productID) desc);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public ArrayList<Product> get4NewProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "select top 4 * from Products order by ProductID desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                products.add(p);
            }
        } catch (SQLException e) {
        }
        return products;
    }

    public Product getProductsByID(int productID) {
        Product p = null;
        try {
            String sql = "select * from Products where ProductID =" + productID + ";";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");
                p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
            }
        } catch (SQLException e) {
        }
        return p;

    }
    public int update(Product p) {
        int result = 0;
        String sql = "update Products set ProductName=?, CategoryID = ?,QuantityPerUnit = ?,UnitPrice = ?,"
                + "UnitsInStock = ?,UnitsOnOrder = ?,ReorderLevel = ?,Discontinued = ? where ProductID = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getProductName());
            st.setInt(2, p.getCategoryID());
            st.setString(3, p.getQuantityPerUnit());
            st.setDouble(4, p.getUnitPrice());
            st.setInt(5, p.getUnitsInStock());
            st.setInt(6, p.getUnitsOnOrder());
            st.setInt(7, p.getReorderLevel());
            st.setBoolean(8, p.isDiscontinued());
            st.setInt(9, p.getProductID());
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public void delete(int id) {
        String sql = "delete from Products where ProductID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int insert(Product p) {
        int result = 0;
        String sql = "insert into Products (ProductName,CategoryID,QuantityPerUnit,UnitPrice,UnitsInStock,UnitsOnOrder,ReorderLevel,Discontinued)"
                + "values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getProductName());
            st.setInt(2, p.getCategoryID());
            st.setString(3, p.getQuantityPerUnit());
            st.setDouble(4, p.getUnitPrice());
            st.setInt(5, p.getUnitsInStock());
            st.setInt(6, p.getUnitsOnOrder());
            st.setInt(7, p.getReorderLevel());
            st.setBoolean(8, p.isDiscontinued());
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
