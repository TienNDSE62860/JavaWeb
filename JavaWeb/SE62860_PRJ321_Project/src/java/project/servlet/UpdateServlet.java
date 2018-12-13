/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.tbl_leave.Tbl_leaveDAO;

/**
 *
 * @author tien29214
 */
public class UpdateServlet extends HttpServlet {
    
    private final String updateErrPage = "updateErrPage.html";
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
        String employeeID = request.getParameter("txtEmpID");
        String rejectReason = request.getParameter("txtRejectReason");
        boolean accept = false;
        String button = request.getParameter("btAction");
        String searchDateFrom = request.getParameter("lastDateFrom");
        String searchDateTo = request.getParameter("lastDateTo");
        String depID = request.getParameter("getDepID");
        Tbl_leaveDAO dao = new Tbl_leaveDAO();
        String urlRewriting = updateErrPage;
        try  {
            /* TODO output your page here. You may use following sample code. */
           if (button.equals("Accept")){
               accept = true;
               boolean result = dao.updateRejectReason(employeeID, accept, rejectReason);
               if (result){
                   urlRewriting = "search?"
                           + "&txtSearchFrom=" + searchDateFrom
                           + "&txtSearchTo=" + searchDateTo
                           + "&getDepID=" + depID;
                        
                  }
               } else if (button.equals("Reject")){
                   boolean result = dao.updateRejectReason(employeeID, accept, rejectReason);
                   if (result){
                     urlRewriting = "search?"
                             + "&txtSearchFrom=" + searchDateFrom
                             + "&txtSearchTo=" + searchDateTo
                             + "&getDepID=" + depID;                                  
                   }
               }
           
        }  catch (SQLException ex) {
            log("UpdateServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateServlet_Naming " + ex.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
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
