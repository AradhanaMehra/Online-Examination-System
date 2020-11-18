/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
            
            String mail=request.getParameter("email");
            String password=request.getParameter("pass");
            String type=request.getParameter("Login");
             
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ca2?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root", "root");
            if(type.equals("Teacher"))
            {    
                    String sql = "select * from teacherlogin where Email=? and Password=?";
                    PreparedStatement ps=conn.prepareStatement(sql);
                    ps.setString(1, mail);
                    ps.setString(2, password);
                    ResultSet rs=ps.executeQuery();
                    
                    if(rs.next()){
                       response.sendRedirect("homepage.html");
                    }  
                    conn.close();
                    
             }
            else
            {
                String sql = "select * from loginstudent where Email=? and Password=?";
                    PreparedStatement ps=conn.prepareStatement(sql);
                    ps.setString(1, mail);
                    ps.setString(2, password);
                    ResultSet rs=ps.executeQuery();
                    
                    if(rs.next()){
                       response.sendRedirect("pagehome.html");
                    }  
                    conn.close();
                
                
            }
                    HttpSession hs= request.getSession();
                    hs.setAttribute("email", mail);
            
        }
         catch(Exception e){
                out.println("Exception"+e.getMessage());
                
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