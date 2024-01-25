<%--
  Created by IntelliJ IDEA.
  User: jmora
  Date: 5/01/24
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<c:choose>
<c:when test="${carro.items.isEmpty()}">
<div class="alert alert-warning">Lo Sentimos, no hay productos en el carro de compras</div>
</c:when>

<c:otherwise>
<form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">
  <table class="table table-hover table-striped">
    <tr>
      <th>Id</th>
      <th>Nombre</th>
      <th>Precio</th>
      <th>Cantidad</th>
      <th>Total</th>
      <th>Borrar</th>
    </tr>

    <c:forEach items="${carro.items}" var="itemCarro">
    <tr>
      <td>${itemCarro.productDTO.id}</td>
      <td>${itemCarro.productDTO.name}</td>
      <td>${itemCarro.productDTO.price}</td>
      <td><input type="text" name="cant_${itemCarro.productDTO.id}" value="${itemCarro.cantidad}"></td>
      <td>${itemCarro.total}</td>
      <td><input type="checkbox" value="${itemCarro.productDTO.id}" name="deleteProductos"></td>
    </tr>
    </c:forEach>

    <tr>
      <td colspan="5" style="text-align: right"> Total </td>
      <td> ${carro.total} </td>
    </tr>
  </table>
  <input class="btn btn-primary" type="submit" value="Actualizar">
</form>
</c:otherwise>
</c:choose>

<jsp:include page="layout/footer.jsp" />
