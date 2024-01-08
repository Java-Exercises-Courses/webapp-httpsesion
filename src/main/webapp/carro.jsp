<%--
  Created by IntelliJ IDEA.
  User: jmora
  Date: 5/01/24
  Time: 11:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="models.*" %>
<%
Carro carro = (Carro) session.getAttribute("carro");

%>
<html>
<head>
    <title>Carro de compras</title>
</head>
<body>
<h1>Carro de compras</h1>
<%if (carro.getItems().isEmpty()) {%>
<p>Lo Sentimos, no hay productos en el carro de compras</p>
<%} else {%>

<form name="formcarro" action="<%=request.getContextPath()%>/carro/actualizar" method="post">
  <table>
    <tr>
      <th>Id</th>
      <th>Nombre</th>
      <th>Precio</th>
      <th>Cantidad</th>
      <th>Total</th>
      <th>Borrar</th>
    </tr>

    <%for (ItemCarro itemCarro: carro.getItems()) {%>
    <tr>
      <td><%=itemCarro.getProductDTO().getId()%></td>
      <td><%=itemCarro.getProductDTO().getName()%></td>
      <td><%=itemCarro.getProductDTO().getPrice()%></td>
      <td><input type="text" name="cant_<%=itemCarro.getProductDTO().getId()%>" value="<%=itemCarro.getCantidad()%>"></td>
      <td><%=itemCarro.getTotal()%></td>
      <td><input type="checkbox" value="<%=itemCarro.getProductDTO().getId()%>" name="deleteProductos"></td>
    </tr>
    <%}%>

    <tr>
      <td colspan="4" style="text-align: right"> Total </td>
      <td> <%=carro.getTotal()%> </td>
    </tr>
  </table>
  <input type="submit" value="Actualizar">
</form>
<%}%>


<p><a href="<%=request.getContextPath()%>/productos">Seguir comprando</a></p>
<p><a href="<%=request.getContextPath()%>/index.html">Volver</a></p>

</body>
</html>
