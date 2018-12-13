<%-- 
    Document   : depPage
    Created on : Jun 24, 2018, 12:49:59 AM
    Author     : tien29214
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="myTag"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Department Page</title>
    </head>
    <body>
        <font color="red">Welcome, ${sessionScope.USERNAME}</font><br/><br/>
        <c:set var="dep" value="${sessionScope.DEPARTMENT}"/>
        <c:set var="count" value="${sessionScope.COUNT}"/>
        <h3>Department Name :  ${dep.depID} / ${dep.name}</h3> <h3 style="margin-right: 50px">Total of Employee: ${count}</h3>       
        <br/><br/>
        

        <form action="search">
            <c:set var="errors" value="${requestScope.CREATEERROR}"/>
            From : <input type="text" name="txtSearchFrom" value=""  size="30"/> 
            To : <input type="text" name="txtSearchTo" value=""  size="30"/>
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
            <input type="submit" value="Search" name="btAction"/><br/>
            <input type="hidden" name="getDepID" value="${dep.depID}"/>
          
        </form>    
        <br/>
        <c:set var="searchFrom" value="${param.txtSearchFrom}"/>
        <c:set var="searchTo" value="${param.txtSearchTo}"/>
        <c:if test="${not empty searchFrom and not empty searchTo}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>employeeID</th>
                            <th>Name</th>
                            <th>Salary</th>
                            <th>Address</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Request Reason</th>
                            <th>Reject Reason</th>
                            <th>Action</th>
                            <th>Change</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="row" items="${result}" varStatus="counter">
                        <form action="update">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${row.employeeID}
                                    <input type="hidden" name="txtEmpID" value="${row.employeeID}" />
                                </td>
                                <td>
                                    ${row.name}
                                </td>
                                <td>
                                    ${row.salary}
                                </td>
                                <td>
                                    ${row.address}
                                </td>
                                <td>
                                    ${row.email}
                                </td>
                                <td>
                                    ${row.phone}
                                </td>
                                <td>
                                    ${row.requestReason}
                                </td>
                                <td>                                  
                                    <input type="text" name="txtRejectReason" value="${row.rejectReason}" />

                                </td>
                                <td>                                                                  
                                    <input type="submit" value="Accept" name="btAction" />
                                    <input type="submit" value="Reject" name="btAction" />
                                    <input type="hidden" name="lastDateFrom" value="${param.txtSearchFrom}" />
                                    <input type="hidden" name="lastDateTo" value="${param.txtSearchTo}" />
                                    <input type="hidden" name="getDepID" value="${dep.depID}"/>
                                </td>
                                <td>
                                    <c:url var="change" value="getEmpID">
                                        <c:param name="employeeID" value="${row.employeeID}"/>
                                    </c:url>
                                    <a href="${change}">Change</a>                               
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>No record is matched</h2>
        </c:if>
    </c:if>
    <br/>
    <a href="login.html">Back to login page</a>

</body>
</html>
