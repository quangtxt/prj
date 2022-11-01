/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Employees {
    private int EmployeeID;
    private String LastName;
    private String FirstName;
    private int DepartmentID;
    private String Title;
    private String TitleOfCourtesy;
    private Date BirthDate;
    private Date HireDate;
    private String Address;

    public Employees(int EmployeeID, String LastName, String FirstName, int DepartmentID, String Title, String TitleOfCourtesy, Date BirthDate, Date HireDate, String Address) {
        this.EmployeeID = EmployeeID;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.DepartmentID = DepartmentID;
        this.Title = Title;
        this.TitleOfCourtesy = TitleOfCourtesy;
        this.BirthDate = BirthDate;
        this.HireDate = HireDate;
        this.Address = Address;
    }


    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getTitleOfCourtesy() {
        return TitleOfCourtesy;
    }

    public void setTitleOfCourtesy(String TitleOfCourtesy) {
        this.TitleOfCourtesy = TitleOfCourtesy;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date BirthDate) {
        this.BirthDate = BirthDate;
    }

    public Date getHireDate() {
        return HireDate;
    }

    public void setHireDate(Date HireDate) {
        this.HireDate = HireDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    
}
