<%--
  Created by IntelliJ IDEA.
  User: jmora
  Date: 14/01/24
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<form action="${pageContext.request.contextPath}/usuarios/form" method="post">
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="username">Username: </label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="username" id="username" value="${user.username}">
        </div>
        <c:if test="${errores != null && errores.containsKey('username')}">
            <div style="color: #af2424">${errores.username}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="password">Password: </label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="password" id="password" value="${user.password}">
        </div>
        <c:if test="${errores != null && errores.containsKey('password')}">
            <div style="color: #af2424">${errores.password}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="email">Email: </label>
        <div class="col-sm-4">
            <input class="form-control" type="text" name="email" id="email" value="${user.email}">
        </div>
        <c:if test="${errores != null && errores.containsKey('email')}">
            <div style="color: #af2424">${errores.email}</div>
        </c:if>
    </div>
    <div class="row mb-2">
        <input class="btn btn-primary" type="submit" value="${(user.id != null && user.id > 0) ? "Editar" : "Crear"}">
    </div>
    <input type="hidden" name="id" value="${user.id}">
</form>
<jsp:include page="layout/footer.jsp" />
