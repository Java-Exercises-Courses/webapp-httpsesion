<%--
  Created by IntelliJ IDEA.
  User: jmora
  Date: 9/01/24
  Time: 7:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario Producto</title>
</head>
<body>
<h1>Formulario Producto</h1>
<form action="${pageContext.request.contextPath}/productos/form" method="post">
  <div>
    <label for="name">Nombre: </label>
    <div>
      <input type="text" name="name" id="name" value="${producto.name}">
    </div>
    <c:if test="${errores != null && errores.containsKey('name')}">
      <div style="color:#af2424">${errores.name}</div>
    </c:if>
  </div>
  <div>
    <label for="price">Precio: </label>
    <div>
      <input type="number" name="price" id="price" value="${producto.price > 0 ? producto.price: ""}">
    </div>
    <c:if test="${errores != null && errores.containsKey('price')}">
      <div style="color:#af2424">${errores.price}</div>
    </c:if>
  </div>
  <div>
    <label for="sku">Sku: </label>
    <div>
      <input type="text" name="sku" id="sku" value="${producto.sku}">
    </div>
    <c:if test="${errores != null && errores.containsKey('sku')}">
      <div style="color:#af2424">${errores.sku}</div>
    </c:if>
  </div>
  <div>
    <label for="fecha_registro">Fecha Registro: </label>
    <div>
      <input type="date" name="fecha_registro" id="fecha_registro" value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
    </div>
    <c:if test="${errores != null && errores.containsKey('fecha_registro')}">
      <div style="color:#af2424">${errores.fecha_registro}</div>
    </c:if>
  </div>
  <div>
    <label for="categoria">Categoria</label>
    <div>
      <select name="categoria" id="categoria">
        <option value="">--- seleccionar ---</option>
        <c:forEach items="${categorias}" var="c">
          <option value="${c.id}" ${c.id.equals(producto.categoria.id) ? "selected" : "" }>${c.nombre}</option>
        </c:forEach>
      </select>
    </div>
    <c:if test="${errores != null && errores.containsKey('categoria')}">
      <div style="color:#af2424">${errores.categoria}</div>
    </c:if>
  </div>
  <div>
  <input type="submit" value="${(producto.id != null && producto.id > 0) ? "Editar" : "Crear"}">
  </div>
  <input type="hidden" name="id" value="${producto.id}">
</form>
</body>
</html>
