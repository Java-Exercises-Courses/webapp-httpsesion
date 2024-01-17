package controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Carro;
import models.ItemCarro;
import models.ProductDTO;
import services.ProductService;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarAlCarroServlet extends HttpServlet {
    @Inject
    private Carro carro;

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Optional<ProductDTO> productDTO = productService.getById(id);

        if (productDTO.isPresent()) {
            ItemCarro itemCarro = new ItemCarro(1, productDTO.get());
            carro.addItem(itemCarro);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
