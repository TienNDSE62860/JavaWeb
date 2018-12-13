<%-- 
    Document   : changeEmp
    Created on : Jun 30, 2018, 12:26:35 AM
    Author     : tien29214
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="myTag"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change</title>
    </head>
    <body>
        <c:set var="dep" value="${sessionScope.DEPARTMENT}"/>
        <c:set var="emp" value="${requestScope.EMPID}"/>
        
        <h3>Department Name:  ${dep.name} </h3><br/>
        <form action="change">
            <input type="hidden" name="employeeID" value="${emp}"/>
            <input type="hidden" name="depID" value="${dep.depID}"/>
            To Department: <select name="selectDep">
                <myTag:depListGrid datasource="SE62860" sqlStatement="Select depID from tbl_department"/>
            </select><br/>
            Salary: <input type="text" name="txtSalary" value="" /><br/>
            <input type="submit" value="Confirm" name="btAction"/>
            <input type="submit" value="Cancel" name="btAction"/> 
        </form><br/>

        <a href="depPage.jsp">Back</a>
    </body>
</html>
