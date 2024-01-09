<%--
  Created by IntelliJ IDEA.
  User: jmora
  Date: 9/01/24
  Time: 7:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, models.Categoria" %>
<%@ page import="java.util.List" %>
<%
  List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>
<html>
<head>
    <title>Formulario Producto</title>
</head>
<body>
<h1>Formulario Producto</h1>
<form action="<%=request.getContextPath()%>/productos/form" method="post">
  <div>
    <label for="name">Nombre: </label>
    <div>
      <input type="text" name="name" id="name">
    </div>
  </div>
  <div>
    <label for="price">Precio: </label>
    <div>
      <input type="number" name="price" id="price">
    </div>
  </div>
  <div>
    <label for="sku">Sku: </label>
    <div>
      <input type="text" name="sku" id="sku">
    </div>
  </div>
  <div>
    <label for="fecha_registro">Fecha Registro: </label>
    <div>
      <input type="date" name="fecha_registro" id="fecha_registro">
    </div>
  </div>
  <div>
    <label for="categoria">Categoria</label>
    <div>
      <select name="categoria" id="categoria">
        <option value="">--- seleccionar ---</option>
        <%for (Categoria c: categorias){%>
          <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
        <%}%>
      </select>
    </div>
  </div>
  <div>
    <input type="submit" value="Crear">
  </div>
</form>
</body>
</html>
