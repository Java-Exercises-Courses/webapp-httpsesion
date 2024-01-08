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
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarAlCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ProductService service = new ProductServiceImpl();
        Optional<ProductDTO> productDTO = service.getById(id);

        if (productDTO.isPresent()) {
            ItemCarro itemCarro = new ItemCarro(1, productDTO.get());

            HttpSession session = req.getSession();
            Carro carro;

            if (session.getAttribute("carro") == null) {
                carro = new Carro();
                session.setAttribute("carro", carro);
            } else {
                carro = (Carro) session.getAttribute("carro");
            }

            carro.addItem(itemCarro);
        }
        resp.sendRedirect(req.getContextPath() + "/ver-carro");
    }
}
