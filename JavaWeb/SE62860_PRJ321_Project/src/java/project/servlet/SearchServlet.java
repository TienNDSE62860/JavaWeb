/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.leaveemp.LeaveEmp;
import project.tbl_leave.Tbl_leaveDAO;
import project.tbl_leave.Tbl_leaveRequestError;

/**
 *
 * @author tien29214
 */
public class SearchServlet extends HttpServlet {

    private final String showSearchResult = "depPage.jsp";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String depID = request.getParameter("getDepID");
        
        System.out.println(depID);
        String fromDate = request.getParameter("txtSearchFrom");
        String toDate = request.getParameter("txtSearchTo");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        boolean errFound = false;
        Tbl_leaveRequestError error = new Tbl_leaveRequestError();
        try {
            /* TODO output your page here. You may use following sample code. */

            Tbl_leaveDAO dao = new Tbl_leaveDAO();
            if (dao.checkDate(fromDate) == null) {
                errFound = true;
                error.setDateFromErr("Invalid Date From !!! Format yyyy-MM-dd");
            }
            if (dao.checkDate(toDate) == null) {
                errFound = true;
                error.setDateToErr("Invalid Date To !!! Format yyyy-MM-dd");
            }
            if (errFound) {
                request.setAttribute("CREATEERROR", error);
            } else {
                //transfer qua util
                java.util.Date dateFrom = (java.util.Date) df.parse(fromDate);
                java.util.Date dateTo = (java.util.Date) df.parse(toDate);
                //transfer qua sql
                java.sql.Date sqlFromDate = new java.sql.Date(dateFrom.getTime());
                java.sql.Date sqlToDate = new java.sql.Date(dateTo.getTime());
                //search
                dao.searchValue(sqlFromDate, sqlToDate, depID);
                List<LeaveEmp> result = dao.getListLeaveEmp();

                request.setAttribute("SEARCHRESULT", result);
            }

        } catch (SQLException ex) {
            log("SearchServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchServlet_Naming " + ex.getMessage());
        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(showSearchResult);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
