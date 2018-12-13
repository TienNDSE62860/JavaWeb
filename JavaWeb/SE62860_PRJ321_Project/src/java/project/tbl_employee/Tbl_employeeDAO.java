/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_employee;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import project.utils.DBConnection;

/**
 *
 * @author tien29214
 */
public class Tbl_employeeDAO implements Serializable{
    private Tbl_employeeDTO empAccounts;
    
    public Tbl_employeeDTO getEmpAccounts(){
        return empAccounts;
    }
   
    public void getAccount(String username) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "Select * from tbl_employee where employeeID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()){
                    String employeeID = rs.getString("employeeID");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    boolean manager = rs.getBoolean("manager");
                    String depID = rs.getString("depID");
                    float salary = rs.getFloat("salary");
                    Tbl_employeeDTO dto = new Tbl_employeeDTO(employeeID, name, address, email, phone, manager, depID, salary);
                    this.empAccounts = dto;
                } //end if rs
            } //end if con
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public int getCountEmp(String depID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null){
                String sql = "Select count(*) as 'Count' from tbl_employee where depID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, depID);
                rs = stm.executeQuery();
                if (rs.next()){
                    //get count
                    int count = rs.getInt("Count");
                    return count;
                }
            } //end if con
        } finally{
             if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }
    
    public boolean changeEmp(String employeeID, String depID, float salary) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.getConnection();
            if (con != null){
                String sql =  "Update tbl_employee set depID = ?, salary = ? where employeeID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, depID);
                stm.setFloat(2, salary);
                stm.setString(3, employeeID);
                int result = stm.executeUpdate();
                if (result > 0){
                    return true;
                }
            }
        } finally {
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
    
    
    
}
