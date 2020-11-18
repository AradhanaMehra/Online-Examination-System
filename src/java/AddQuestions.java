/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
@WebServlet(urlPatterns = {"/AddQuestions"})
public class AddQuestions extends HttpServlet {

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
            String question = request.getParameter("ques");
            String op1 = request.getParameter("op1");
            String op2 = request.getParameter("op2");
            String op3 = request.getParameter("op3");
            String op4 = request.getParameter("op4");
            String ans = request.getParameter("ans");
            
            HttpSession hs=request.getSession(false);
            String exam=(String)hs.getAttribute("exam");
            String sql = "insert into questions(question,op1,op2,op3,op4,exam,ans) values(?,?,?,?,?,?,?)";
            
            try{
               
                    
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ca2?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false","root", "root");
                    PreparedStatement ps=conn.prepareStatement(sql);
                    ps.setString(1, question);
                    ps.setString(2, op1);
                   ps.setString(3, op2);
                   ps.setString(4, op3);
                    ps.setString(5, op4);
                   ps.setString(6, exam);
                 ps.setString(7, ans);
                   
                    
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
