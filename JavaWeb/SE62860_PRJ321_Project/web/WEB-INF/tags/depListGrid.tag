<%-- 
    Document   : depListGrid
    Created on : Jul 1, 2018, 8:27:58 PM
    Author     : tien29214
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@attribute name="sqlStatement" required="true"%>
<%@attribute name="datasource" required="true"%>
<%@tag dynamic-attributes="pars" %>

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
                <tbody>
                    <c:forEach var="row" items="${rs.rowsByIndex}" varStatus="counter">
                            <c:forEach var="col" items="${row}">
                                <option>${col}</option>
                            </c:forEach>
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