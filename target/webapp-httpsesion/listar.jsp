<%--
  Created by IntelliJ IDEA.
  User: jmora
  Date: 8/01/24
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<c:if test="${username.present}">
  <div class="alert alert-info"> Hola ${username.get()}, bienvenido! </div>
  <a class="btn btn-primary my-2" href="${pageContext.request.contextPath}/productos/form">Crear [+]</a>
</c:if>
<table class="table table-hover table-striped">
  <tr>
    <th>Nombre</th>
    <th>Categoría</th>
    <th>Precio</th>
    <c:if test="${username.present}">
    <th>Agregar</th>
    <th>Editar</th>
    <th>Eliminar</th>
    </c:if>
  </tr>
  <c:forEach items="${productos}" var="p">
  <tr>
    <td>${p.name}</td>
    <td>${p.categoria.nombre}</td>
    <td>${p.price}</td>
    <c:if test="${username.present}">
    <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}">Agregar al carro</a></td>
    <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/productos/form?id=${p.id}">Editar</a></td>
    <td><a class="btn btn-sm btn-danger" onclick="return confirm('¿Seguro que desea eliminar este producto?');"
            href="${pageContext.request.contextPath}/productos/eliminar?id=${p.id}">Eliminar</a></td>
    </c:if>
  </tr>
  </c:forEach>
</table>
<p>${requestScope.mensaje}</p>
<jsp:include page="layout/footer.jsp" />
