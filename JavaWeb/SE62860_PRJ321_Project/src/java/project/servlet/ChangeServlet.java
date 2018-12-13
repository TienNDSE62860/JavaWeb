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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import project.tbl_employee.Tbl_employeeDAO;

/**
 *
 * @author tien29214
 */
public class ChangeServlet extends HttpServlet {

    private final String success = "requestSuccess.html";
    private final String fail = "updateErrPage.html";
    private final String depPage = "depPage.jsp";

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
        String employeeID = request.getParameter("employeeID");
        String getCurrentDepID = request.getParameter("depID");
        String depID = request.getParameter("selectDep");
        String stringSalary = request.getParameter("txtSalary");
        String button = request.getParameter("btAction");
        String url = fail;
        try {
            /* TODO output your page here. You may use following sample code. */
            if (button.equals("Cancel")) {
                url = depPage;
            } else {
                float salary = Float.parseFloat(stringSalary);
                Tbl_employeeDAO dao = new Tbl_employeeDAO();
                //change
                boolean result = dao.changeEmp(employeeID, depID, salary);
                if (result) {
                    int count = dao.getCountEmp(getCurrentDepID);
                    HttpSession session = request.getSession();
                    session.setAttribute("COUNT", count);
                    url = success;
                }
            }

        } catch (SQLException ex) {
            log("ChangeServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("ChangeServlet_Naming " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
