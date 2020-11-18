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
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
/**
 *
 * @author HP
 */
@WebServlet(urlPatterns = {"/CreateExam"})
public class CreateExam extends HttpServlet {

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
            String Name = request.getParameter("examname");
            String Course = request.getParameter("course");
            String Type=request.getParameter("type");
            HttpSession hs=request.getSession(true);
            hs.setAttribute("exam",Name);
            String sql = "insert into exam(Name,Course,Type) values(?,?,?)";
            
            try{
               
                    
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ca2?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root", "root");
                    PreparedStatement ps=conn.prepareStatement(sql);
                    
                    ps.setString(1, Name);
                    ps.setString(2, Course);
                   ps.setString(3, Type);
                 
                    int i=0;
                    
                    ps.executeUpdate();
                    
                    
                       
                        RequestDispatcher rd = request.getRequestDispatcher("addquestions.html");
                        rd.forward(request, response);
                
                    conn.close();
                
                                
            }
            catch(Exception e){
                out.println("Exception"+e.getMessage());
                
            }

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
