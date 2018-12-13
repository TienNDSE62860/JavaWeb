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
import project.tbl_account.Tbl_accountDAO;
import project.tbl_department.Tbl_departmentDAO;
import project.tbl_department.Tbl_departmentDTO;
import project.tbl_employee.Tbl_employeeDAO;
import project.tbl_employee.Tbl_employeeDTO;

/**
 *
 * @author tien29214
 */
public class LoginServlet extends HttpServlet {

    private final String invalid = "invalid.html";
    private final String empPage = "empPage.jsp";
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
        String url = invalid;
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Tbl_accountDAO dao = new Tbl_accountDAO();
            boolean result = dao.checkLogin(username, password);
            String role = dao.checkRole(username, password);
            if (result){
                if (role.equals("emp")){
                    Tbl_employeeDAO edao = new Tbl_employeeDAO();
                    edao.getAccount(username);
                    //lay account
                    Tbl_employeeDTO dto = edao.getEmpAccounts();
                    HttpSession session = request.getSession();
                    session.setAttribute("GETACCOUNT", dto);
                    url = empPage;
                    //lay username
                    session.setAttribute("USERNAME", username);
                } else if (role.equals("dep")){
                    Tbl_employeeDAO edao = new Tbl_employeeDAO();
                    edao.getAccount(username);
                    Tbl_employeeDTO dto = edao.getEmpAccounts();
                    //lay count emp
                    HttpSession session = request.getSession();
                    int countEmp = edao.getCountEmp(dto.getDepID());
                    session.setAttribute("COUNT", countEmp);
                    //account 
                    session.setAttribute("GETACCOUNT", dto);
                    Tbl_departmentDAO ddao = new Tbl_departmentDAO();
                    ddao.getDepartmentName(dto.getDepID());
                    Tbl_departmentDTO ddto = ddao.getDepartment();
                    session.setAttribute("DEPARTMENT", ddto);
                    session.setAttribute("USERNAME", username);                  
                    url  = depPage;            
                }
            }
        } catch (SQLException ex){
            log("loginServlet_SQL" + ex.getMessage());
        } catch (NamingException ex){
            log("loginServlet_Naming" + ex.getMessage());
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
