/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_employee;

import java.io.Serializable;

/**
 *
 * @author tien29214
 */
public class Tbl_employeeDTO implements Serializable{
    private String employeeID;
    private String name;
    private String address;
    private String email;
    private String phone;
    private boolean manager; 
    private String depID;
    private float salary;

    public Tbl_employeeDTO(){}
       public Tbl_employeeDTO(String employeeID, String name, String address, String email, String phone, boolean manager, String depID, float salary) {
        this.employeeID = employeeID;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.manager = manager;
        this.depID = depID;
        this.salary = salary;
    }

    /**
     * @return the employeeID
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the manager
     */
    public boolean isManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(boolean manager) {
        this.manager = manager;
    }

    /**
     * @return the depID
     */
    public String getDepID() {
        return depID;
    }

    /**
     * @param depID the depID to set
     */
    public void setDepID(String depID) {
        this.depID = depID;
    }

    /**
     * @return the salary
     */
    public float getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(float salary) {
        this.salary = salary;
    }
    
}
