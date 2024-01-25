package controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.entities.ProductDTO;
import services.LoginService;
import services.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productos.html", "/productos", "/"})
public class ProductoServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Connection conn = (Connection) req.getAttribute("conn");

        List<ProductDTO> products = productService.getProducts();
        Optional<String> usernameOptional = loginService.getUsername(req);

        req.setAttribute("productos", products);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", "Listado de productos");
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
