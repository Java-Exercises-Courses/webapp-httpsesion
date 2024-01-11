<%--
  Created by IntelliJ IDEA.
  User: jmora
  Date: 5/01/24
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Carro de compras</title>
</head>
<body>
<h1>Carro de compras</h1>
<c:choose>
<c:when test="${sessionScope.carro.items.isEmpty()}">
<p>Lo Sentimos, no hay productos en el carro de compras</p>
</c:when>

<c:otherwise>
<form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">
  <table>
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
      <td colspan="4" style="text-align: right"> Total </td>
      <td> ${carro.total} </td>
    </tr>
  </table>
  <input type="submit" value="Actualizar">
</form>
</c:otherwise>
</c:choose>

<p><a href="${pageContext.request.contextPath}/productos">Seguir comprando</a></p>
<p><a href="${pageContext.request.contextPath}/index.html">Volver</a></p>

</body>
</html>
