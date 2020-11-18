/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/Evaluate"})
public class Evaluate extends HttpServlet {

    Connection conn = null;
            String url = "jdbc:mysql://localhost:3306/ca2?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false";
            String dbusername = "root";
            String dbpassword = "root";
            Statement statement = null;
            ResultSet resultset = null;
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Evaluate.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Enumeration paramNames = request.getParameterNames();
            statement = conn.createStatement();
            HttpSession session = request.getSession();
            String selectedexam = (String) session.getAttribute("selectedexam");
            //out.println(quiztype);
            int marks = 0;
            
           while(paramNames.hasMoreElements()){
              
           String paramName = (String)paramNames.nextElement();
           resultset = statement.executeQuery("select question,ans from questions where id="+paramName+"");
           
           String [] paramValues = request.getParameterValues(paramName);
           
           for(String paraval:paramValues){
               
                  while (resultset.next()) // Until next row is present otherwise it return false
                    { 
                        String correct = resultset.getString("ans");
                        String question = resultset.getString("question");

                    if(paraval.equals(correct)){
                        marks++;
                      
                    }
                  
                  }
               }
           }
           out.println("<h1>The Examination Has Ended</h1>");
           out.println("<h4>Please Click on the 'Record Answers' Button below to Record them for Evaluation</h4>");
           out.println(" <center><form method=\"POST\" action=\"marks.jsp\">\n" +
"            \n" +
"            <input type=\"hidden\" name=\"uscore\" value="+marks+">\n" +
"            <input type=\"submit\" value=\"Record Answers\">\n" +
"        </form>\n" +
"    </center>");
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
        } catch (SQLException ex) {
            Logger.getLogger(Evaluate.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(Evaluate.class.getName()).log(Level.SEVERE, null, ex);
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
