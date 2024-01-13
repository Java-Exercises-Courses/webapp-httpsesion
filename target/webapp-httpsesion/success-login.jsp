<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="layout/header.jsp" />
<h3 class="alert alert-info">Hola ${usernameOptional.get()}, iniciaste sesión</h3>
<div>
  <a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/productos">Listado de productos</a>
  <a class="btn btn-sm btn-danger" href="${pageContext.request.contextPath}/logout">Cerrar Sesión</a>
</div>
<jsp:include page="layout/footer.jsp" />
