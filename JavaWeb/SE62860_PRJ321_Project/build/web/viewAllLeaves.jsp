<%-- 
    Document   : viewAllLeaves
    Created on : Jun 28, 2018, 5:20:21 PM
    Author     : tien29214
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib tagdir="/WEB-INF/tags/" prefix="myLib"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View All Leaves</title>
    </head>
    <body>
        
        <h3>All Leaves</h3>
        <myLib:allLeavesGrid datasource="SE62860" sqlStatement="Select * from tbl_leave"/>
        <a href="empPage.jsp">Back</a>
    </body>
</html>
