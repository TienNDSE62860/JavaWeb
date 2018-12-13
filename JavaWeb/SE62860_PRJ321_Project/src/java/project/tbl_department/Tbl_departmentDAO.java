/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.tbl_department;

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
public class Tbl_departmentDAO implements Serializable{
    private Tbl_departmentDTO dep;
    public Tbl_departmentDTO getDepartment(){
        return dep;
    }
    public void getDepartmentName(String depID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            if (con != null) {
                String sql = "Select * from tbl_department where depID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, depID);
                rs = stm.executeQuery();
                if (rs.next()){
                    String departID = rs.getString("depID");
                    String name = rs.getString("name");
                    Tbl_departmentDTO dto = new Tbl_departmentDTO(departID, name);
                    //get
                    this.dep = dto;
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
}
