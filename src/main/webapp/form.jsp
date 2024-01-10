<%--
  Created by IntelliJ IDEA.
  User: jmora
  Date: 9/01/24
  Time: 7:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="models.ProductDTO" %>
<%@ page import="models.Categoria" %>
<%
  List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
  ProductDTO productDTO = (ProductDTO) request.getAttribute("producto");
  Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
  String fecha = productDTO.getFechaRegistro() != null
          ? productDTO.getFechaRegistro().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
          : "";
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
      <input type="text" name="name" id="name" value="<%=productDTO.getName() != null ? productDTO.getName() : ""%>">
    </div>
    <%if (errores != null && errores.containsKey("name")){%>
      <div style="color:#af2424"><%=errores.get("name")%></div>
    <%}%>
  </div>
  <div>
    <label for="price">Precio: </label>
    <div>
      <input type="number" name="price" id="price" value="<%=productDTO.getPrice() != 0 ? productDTO.getPrice() : ""%>">
    </div>
    <%if (errores != null && errores.containsKey("price")){%>
      <div style="color:#af2424"><%=errores.get("price")%></div>
    <%}%>
  </div>
  <div>
    <label for="sku">Sku: </label>
    <div>
      <input type="text" name="sku" id="sku" value="<%=productDTO.getSku() != null ? productDTO.getSku() : ""%>">
    </div>
    <%if (errores != null && errores.containsKey("sku")){%>
    <div style="color:#af2424"><%=errores.get("sku")%></div>
    <%}%>
  </div>
  <div>
    <label for="fecha_registro">Fecha Registro: </label>
    <div>
      <input type="date" name="fecha_registro" id="fecha_registro" value="<%=fecha%>">
    </div>
    <%if (errores != null && errores.containsKey("fecha_registro")){%>
    <div style="color:#af2424"><%=errores.get("fecha_registro")%></div>
    <%}%>
  </div>
  <div>
    <label for="categoria">Categoria</label>
    <div>
      <select name="categoria" id="categoria">
        <option value="">--- seleccionar ---</option>
        <%for (Categoria c: categorias){%>
          <option value="<%=c.getId()%>" <%=c.getId().equals(productDTO.getCategoria().getId()) ? "selected" : ""%> ><%=c.getNombre()%></option>
        <%}%>
      </select>
    </div>
    <%if (errores != null && errores.containsKey("categoria")){%>
    <div style="color:#af2424"><%=errores.get("categoria")%></div>
    <%}%>
  </div>
  <div>
  <input type="submit" value="<%=(productDTO.getId() != null && productDTO.getId() > 0) ? "Editar" : "Crear"%>">
  </div>
  <input type="hidden" name="id" value="<%=productDTO.getId()%>">
</form>
</body>
</html>
