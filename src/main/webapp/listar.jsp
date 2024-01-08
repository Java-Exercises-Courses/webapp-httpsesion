<%--
  Created by IntelliJ IDEA.
  User: jmora
  Date: 8/01/24
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.*" %>
<%
List<ProductDTO> productos = (List<ProductDTO>) request.getAttribute("productos");
Optional<String> username = (Optional<String>) request.getAttribute("username");
String mensaje = (String) request.getAttribute("mensaje");
%>
<html>
<head>
    <title>Listado de productos</title>
</head>
<body>
<h1>Listado de productos</h1>
<%if (username.isPresent()){%>
<div> Hola<%=username.get()%>, bienvenido! </div>
<%}%>
<table>
  <tr>
    <th>Nombre</th>
    <th>Categoría</th>
    <th>Precio</th>
    <%if (username.isPresent()){%>
    <th>Agregar</th>
    <%}%>
  </tr>
  <%for(ProductDTO p: productos){%>
  <tr>
    <td><%=p.getName()%></td>
    <td><%=p.getCategoria()%></td>
    <td><%=p.getPrice()%></td>
    <%if (username.isPresent()){%>
    <td><a href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%>">Agregar al carro</a></td>
    <%}%>
  </tr>
  <%}%>
</table>
<p><%=mensaje%></p>
</body>
</html>
