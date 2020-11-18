<%-- 
    Document   : DisplayQuestions
    Created on : 18 Nov, 2020, 5:58:31 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html>
<html>
    <head>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="selectedexam" value="${param.Examination}" scope="session" />
        <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/ca2?useTimeZone=true&serverTimezone=UTC&autoReconnect=true&useSSL=false"
        user="root" password="root"
    />
     
    <sql:query var="mcq"   dataSource="${myDS}">
        SELECT * FROM questions where exam = '${selectedexam}';
    </sql:query>
           
        <br/><br/><br/>    
        <form action="Evaluate" method="POST">
            
            <center><h3> ${selectedexam} Examination</h3></center> 
            <!--counter-->
            <c:set var="count" value="0" scope="page" />

            <c:forEach var="mcqexam" items="${mcq.rows}" >
                    <c:set var="count" value="${count+1}" scope="page" />
                    <ol id="${count}">

                        <h3><span><c:out value="${count}"></c:out>]&nbsp;</span><c:out value="${mcqexam.question}"></c:out></h3>
                        <input type="radio" name="${mcqexam.id}" value="${mcqexam.op1}" required=""><c:out value="${mcqexam.op1}"></c:out><br>
                        <input type="radio" name="${mcqexam.id}" value="${mcqexam.op2}" required=""><c:out value="${mcqexam.op2}"></c:out><br>
                        <input type="radio" name="${mcqexam.id}" value="${mcqexam.op3}" required=""><c:out value="${mcqexam.op3}"></c:out><br>
                        <input type="radio" name="${mcqexam.id}" value="${mcqexam.op4}" required=""><c:out value="${mcqexam.op4}"></c:out>
                        <br>
                        </ol>
                        <br>
                </c:forEach>
                        <button>Next Question</button>
                        
        </form> 
            <span id="timer" style="color:black;"></span>
    </body>
    <script>
    $(document).ready(function(){
              
        $("ol").hide();
       $("#1").first().show();
        var qcounter = 1;
    $("button").click(function(){
        qcounter++;
        $("ol").hide();
        $("#"+qcounter).show();  
    });
});
    
</script>
<script type="text/javascript">
    object.onunload = function(){
               
        $("ol").hide();
       $("#1").first().show();
        var qcounter = 1;
    $("button").click(function(){
        qcounter++;
        $("ol").hide();
        $("#"+qcounter).show()
        });
    };
</script>
<script type="text/javascript">
var count = 1000;
 
function countDown(){
    var timer = document.getElementById("timer");
    if(count > 0){
        count--;
        timer.innerHTML = "Time Remaining: "+count;
        setTimeout("countDown()", 1000)
    }
}
countDown();

</script>
</html>
