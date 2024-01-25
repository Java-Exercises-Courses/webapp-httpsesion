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
<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<form action="${pageContext.request.contextPath}/productos/form" method="post">
  <div class="row mb-2">
    <label class="col-form-label col-sm-2" for="name">Nombre: </label>
    <div class="col-sm-4">
      <input class="form-control" type="text" name="name" id="name" value="${producto.name}">
    </div>
    <c:if test="${errores != null && errores.containsKey('name')}">
      <div style="color:#af2424">${errores.name}</div>
    </c:if>
  </div>
  <div class="row mb-2">
    <label class="col-form-label col-sm-2" for="price">Precio: </label>
    <div class="col-sm-4">
      <input class="form-control" type="number" name="price" id="price" value="${producto.price > 0 ? producto.price: ""}">
    </div>
    <c:if test="${errores != null && errores.containsKey('price')}">
      <div style="color:#af2424">${errores.price}</div>
    </c:if>
  </div>
  <div class="row mb-2">
    <label class="col-form-label col-sm-2" for="sku">Sku: </label>
    <div class="col-sm-4">
      <input class="form-control" type="text" name="sku" id="sku" value="${producto.sku}">
    </div>
    <c:if test="${errores != null && errores.containsKey('sku')}">
      <div style="color:#af2424">${errores.sku}</div>
    </c:if>
  </div>
  <div class="row mb-2">
    <label class="col-form-label col-sm-2" for="fecha_registro">Fecha Registro: </label>
    <div class="col-sm-4">
      <input class="form-control" type="date" name="fecha_registro" id="fecha_registro" value="${producto.fechaRegistro != null ? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
    </div>
    <c:if test="${errores != null && errores.containsKey('fecha_registro')}">
      <div style="color:#af2424">${errores.fecha_registro}</div>
    </c:if>
  </div>
  <div class="row mb-2">
    <label class="col-form-label col-sm-2" for="categoria">Categoria</label>
    <div class="col-sm-4">
      <select class="form-select" name="categoria" id="categoria">
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
  <div class="row mb-2">
    <div>
      <input class="btn btn-primary" type="submit" value="${(producto.id != null && producto.id > 0) ? "Editar" : "Crear"}">
    </div>
  </div>
  <input type="hidden" name="id" value="${producto.id}">
</form>
</div>
</body>
</html>
