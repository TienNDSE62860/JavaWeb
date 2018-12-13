<%-- 
    Document   : allLeavesGrid
    Created on : Jun 28, 2018, 5:03:06 PM
    Author     : tien29214
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="sqlStatement" required="true"%>
<%@attribute name="datasource" required="true"%>
<%@tag dynamic-attributes="pars" %>
<%-- any content can be specified here e.g.: --%>
<c:catch var="ex">
    <sql:setDataSource var="con" dataSource="${datasource}"/>
    <c:if test="${not empty con}">
        <sql:query var="rs" dataSource="${con}">
            ${sqlStatement}
            <c:forEach var="entry" items="${pars}">
                <sql:param value="${entry.value}"/>
            </c:forEach>
        </sql:query>
        <c:if test="${not empty rs}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                            <c:forEach var="colName" items="${rs.columnNames}">
                            <th>${colName}</th>
                            </c:forEach>
                        
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${rs.rowsByIndex}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <c:forEach var="col" items="${row}">
                                <td>${col}</td>
                            </c:forEach>
                           
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
</c:catch>
<c:if test="${not empty ex}">
    <font color="red">
    ${ex}
    </font>
</c:if>