/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.tbl_leave.Tbl_leaveDAO;
import project.tbl_leave.Tbl_leaveRequestError;

/**
 *
 * @author tien29214
 */
public class RequestServlet extends HttpServlet {

    private final String success = "requestSuccess.html";
    private final String fail = "empPage.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String dateFrom = request.getParameter("txtDateFrom");
        String dateTo = request.getParameter("txtDateTo");

        String reason = request.getParameter("txtReason");
        String empID = request.getParameter("employeeID");
        String urlRewriting = fail;
        boolean errFound = false;
        Tbl_leaveRequestError error = new Tbl_leaveRequestError();

        try {
            if (dateFrom.trim().length() == 0) {
                errFound = true;
                error.setDateFromErr("Input Date From !!!");
            }
            if (dateTo.trim().length() == 0) {
                errFound = true;
                error.setDateToErr("Input Date To !!!");
            }
            Tbl_leaveDAO dao = new Tbl_leaveDAO();
            if (dao.checkDate(dateFrom) == null) {
                errFound = true;
                error.setDateFromErr("Invalid Date From !!! Format yyyy-MM-dd");
            }
            if (dao.checkDate(dateTo) == null) {
                errFound = true;
                error.setDateToErr("Invalid Date To !!! Format yyyy-MM-dd");
            }
            if (errFound) {
                request.setAttribute("CREATEERRORS", error);
            } else {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                //transfer qua util
                java.util.Date fromDate = (java.util.Date) df.parse(dateFrom);
                java.util.Date toDate = (java.util.Date) df.parse(dateTo);
                //transfer qua sql
                java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
                java.sql.Date sqlToDate = new java.sql.Date(toDate.getTime());
                //update
                boolean result = dao.updateLeave(empID, sqlFromDate, sqlToDate, reason);
                if (result) {
                    urlRewriting = success;
                }
            }
        } catch (SQLException ex) {
            log("RequestServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("RequestServlet_Naming " + ex.getMessage());
        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
