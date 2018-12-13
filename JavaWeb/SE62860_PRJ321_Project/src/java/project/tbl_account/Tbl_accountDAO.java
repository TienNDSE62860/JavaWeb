/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_account;

import project.tbl_employee.Tbl_employeeDTO;
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
public class Tbl_accountDAO implements Serializable{
    
    private Tbl_employeeDTO empAccounts;
    public Tbl_employeeDTO getEmpAccounts(){
        return empAccounts;
    }
    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "Select * from tbl_account where accountID=? and password=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
            } //end if con
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    public String checkRole(String username, String password) throws SQLException, NamingException{
        String roleUser = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "Select role from tbl_account where accountID=? and password=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int role = rs.getInt("role");
                    //check role
                    if (role == 1){
                        roleUser = "emp";
                    } else if (role == 2){
                        roleUser = "dep";
                    }
                } //end if rs
            } //end if con
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return roleUser;
    }
    
}
