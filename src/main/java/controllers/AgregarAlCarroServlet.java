package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Carro;
import models.ItemCarro;
import models.ProductDTO;
import services.ProductService;
import services.impl.ProductServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarAlCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        ProductService service = new ProductServiceImpl(conn);

        Long id = Long.parseLong(req.getParameter("id"));
        Optional<ProductDTO> productDTO = service.getById(id);

        if (productDTO.isPresent()) {
            ItemCarro itemCarro = new ItemCarro(1, productDTO.get());

            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            carro.addItem(itemCarro);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
