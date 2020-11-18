<%-- 
    Document   : marks
    Created on : 18 Nov, 2020, 8:03:19 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/ca2?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false"
        user="root" password="root"
    />
     
    <sql:update var="mcq"   dataSource="${myDS}">
        insert into marks(Studentmail,Exam,Marks) values(?,?,?);
         <sql:param value="${email}" />
         <sql:param value="${selectedexam}" />
         <sql:param value="${param.uscore}" />
    </sql:update>
       
        
 
        <c:redirect url="pagehome.html"></c:redirect>    
      
      
    </body>
</html>
</html>
