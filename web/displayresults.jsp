<%-- 
    Document   : displayresults
    Created on : 18 Nov, 2020, 9:10:20 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    
<head>
    <style>#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #fdfbd2;
  color: black;
}
</style>
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
        SELECT * FROM marks;
    </sql:query>
     
    <div align="center">
        <table id="customers" border="1" cellpadding="5">
            <caption><h2>Student Results</h2></caption>
            <tr>
                <th>ID</th>
                <th>Student Mail</th>
                <th>Exam</th>
                <th>Marks</th>
            </tr>
            <c:forEach var="user" items="${exams.rows}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.Studentmail}" /></td>
                    <td><c:out value="${user.Exam}" /></td>
                    <td><c:out value="${user.Marks}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
