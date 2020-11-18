<%-- 
    Document   : displayexam
    Created on : 18 Nov, 2020, 3:07:34 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    
<head>
    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display Examinations</title>
</head>
<body>
    <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/ca2?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false"
        user="root" password="root"
    />
     
    <sql:query var="exams"   dataSource="${myDS}">
        SELECT * FROM exam;
    </sql:query>
     
    <div align="center">
        
            <caption><h2>List of Examinations Available</h2></caption>
            
       
       <form name="frmMain" id="frmMain" method="post" action="DisplayQuestions.jsp">
    <select name="Examination">
        <option value="SELECT">Select Examination</option>
        <c:forEach var="user" items="${exams.rows}">
        <option name="option" value="<c:out value="${user.Name}"/>">
            <c:out value="${user.Name}"/>
        </option>
        </c:forEach>
    </select>
           <button type="submit" class="submit">Click to Start Exam</button>
</form>
    </div>
</body>
</html>