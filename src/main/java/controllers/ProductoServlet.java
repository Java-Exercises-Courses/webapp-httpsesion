package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ProductDTO;
import services.LoginService;
import services.ProductService;
import services.impl.LoginServiceImpl;
import services.impl.ProductServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Connection conn = (Connection) req.getAttribute("conn");

        ProductService service = new ProductServiceImpl(conn);
        List<ProductDTO> products = service.getProducts();

        LoginService loginService = new LoginServiceImpl();
        Optional<String> usernameOptional = loginService.getUsername(req);

        req.setAttribute("productos", products);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", "Listado de productos");
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
