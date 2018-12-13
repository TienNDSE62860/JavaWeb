/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_leave;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import project.leaveemp.LeaveEmp;
import project.utils.DBConnection;

/**
 *
 * @author tien29214
 */
public class Tbl_leaveDAO implements Serializable{
    private List<LeaveEmp> listLeaveEmp;
    private List<Tbl_leaveDTO> listLeavesDTO;
    public List<LeaveEmp> getListLeaveEmp(){
        return listLeaveEmp;
    }
    public List<Tbl_leaveDTO> getListLeavesDTO(){
        return listLeavesDTO;
    }
    public boolean updateLeave(String empID, Date dateFrom, Date dateTo, String reason) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.getConnection();
            if (con != null){
                String sql = "Insert into tbl_leave(leaveID,fromDate,toDate,accept,empID,requestReason)"
                        + " values((Select ISNULL(MAX(leaveID) + 1, 0) from tbl_leave),?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setDate(1, dateFrom);
                stm.setDate(2, dateTo);
                stm.setBoolean(3, false);
                stm.setString(4, empID);
                stm.setString(5, reason);
                int row = stm.executeUpdate();
                if (row > 0){
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
    
    
    public String checkDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(date);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }
    
    public void searchValue(Date fromDate, Date toDate, String depID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null){
                String sql = "Select employeeID, name, salary, address, email, phone, requestReason, rejectReason"
                        + " from [tbl_employess] join tbl_leave on [tbl_employee].employeeID = tbl_leave.empID"
                        + " where fromDate = ? and toDate = ? and depID = ? and accept = 'False'";
                stm = con.prepareStatement(sql);
                stm.setDate(1, fromDate);
                stm.setDate(2, toDate);
                stm.setString(1, depID);
                rs = stm.executeQuery();
                while (rs.next()){
                    String employeeID = rs.getString("employeeID");
                    String name = rs.getString("name");
                    float salary = rs.getFloat("salary");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String requestReason = rs.getString("requestReason");
                    String rejectReason = rs.getString("rejectReason");
                    LeaveEmp le = new LeaveEmp(employeeID, name, salary, address, name, phone, requestReason, rejectReason);
                    if (listLeaveEmp == null){
                        this.listLeaveEmp = new ArrayList<>();
                    }
                    this.listLeaveEmp.add(le);
                }
            }
        } finally {
            if (rs != null){
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }

    }
    
    public boolean updateRejectReason(String employeeID, boolean accept, String rejectReason) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.getConnection();
            if (con != null){
                String sql =  "Update tbl_leave set accept = ?, rejectReason = ? where empID = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, accept);
                stm.setString(2, rejectReason);
                stm.setString(3, employeeID);
                int result = stm.executeUpdate();
                if (result > 0){
                    return true;
                }
            } //end if con
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
