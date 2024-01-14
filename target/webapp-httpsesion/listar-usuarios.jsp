<%--
  Created by IntelliJ IDEA.
  User: jmora
  Date: 14/01/24
  Time: 9:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<c:if test="${username.present}">
    <a href="${pageContext.request.contextPath}/usuarios/form">Crear [+]</a>
</c:if>
<table class="table table-hover table-striped">
    <tr>
        <th>username</th>
        <th>password</th>
        <th>email</th>
        <c:if test="${username.present}">
            <th>Editar</th>
            <th>Eliminar</th>
        </c:if>
    </tr>
    <c:forEach items="${users}" var="u">
    <tr>
        <td>${u.username}</td>
        <td>${u.password}</td>
        <td>${u.email}</td>
        <c:if test="${username.present}">
            <td><a href="${pageContext.request.contextPath}/usuarios/form?id=${u.id}"></a></td>
            <td><a onclick="return confirm('¿Seguro que desea eliminar este producto?')" href="${pageContext.request.contextPath}/usuarios/eliminar?id=4${u.id}">Eliminar</a></td>
        </c:if>
    </tr>
    </c:forEach>
</table>
<jsp:include page="layout/footer.jsp" />