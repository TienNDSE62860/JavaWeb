/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author tien29214
 */
public class DBConnection {
    public static Connection getConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcatCt = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcatCt.lookup("SE62860");
        Connection con = ds.getConnection();
        return con;
    }
}
