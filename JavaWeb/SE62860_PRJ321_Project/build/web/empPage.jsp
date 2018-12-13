<%-- 
    Document   : empPage
    Created on : Jun 23, 2018, 11:31:38 PM
    Author     : tien29214
--%>
<%@page import="project.tbl_leave.tbl_leaveRequestError" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Page</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.USERNAME}</font>
        <h1>Emp Page</h1>
        <c:set var="account" value="${sessionScope.GETACCOUNT}"/>

        EmployeeID : ${account.employeeID}<br/>
        Name :      ${account.name}<br/>
        Department : ${account.depID}<br/>
        Salary :    ${account.salary} USD<br/>
        Leave Date <br/>
        
        <form action="request">
            <c:set var="errors" value="${requestScope.CREATEERRORS}"/>
            From   <input type="text" name="txtDateFrom" value="" size="20"/>
            To     <input type="text" name="txtDateTo" value="" size="20" /><br/>
            <c:if test="${not empty errors.dateFromErr}">
                <font color="red">
                    ${errors.dateFromErr}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.dateToErr}">
                <font color="red">
                    ${errors.dateToErr}
                </font><br/>
            </c:if>    
            Reason <input type="text" name="txtReason" value="" size="100"/><br/>
            <input type="submit" value="Request" name="btAction"/>
            <input type="hidden" name="employeeID" value="${account.employeeID}"/>
        </form>
        <br/>
        
        <a href="viewAllLeaves.jsp">View all Leaves</a><br/>
        
        <a href="login.html">Back to login page</a>
    </body>
</html>
