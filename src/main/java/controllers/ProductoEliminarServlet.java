package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ProductDTO;
import services.ProductService;
import services.impl.ProductServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/eliminar")
public class ProductoEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductService productService= new ProductServiceImpl(conn);
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {
            Optional<ProductDTO> o = productService.getById(id);
            if (o.isPresent()){
                productService.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/productos");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el producto.");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El id del producto es necesario.");
        }
    }
}
