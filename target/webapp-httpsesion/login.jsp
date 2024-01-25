<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="layout/header.jsp" />
<h3>${title}</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
  <div class="row my-2">
    <label for="username"  class="col-form-label col-sm-2">Username: </label>
    <div class="col-sm-4">
      <input type="text" name="username" id="username" class="form-control" >
    </div>
  </div>
  <div class="row my-2">
    <label for="password" class="col-form-label col-sm-2">Password: </label>
    <div  class="col-sm-4">
      <input type="text" name="password" id="password" class="form-control">
    </div>
  </div>
  <div class="row my-2">
    <div class="col col-7">
    <input type="submit" value="Login" class="btn btn-primary">
    </div>
  </div>
</form>
<jsp:include page="layout/footer.jsp" />