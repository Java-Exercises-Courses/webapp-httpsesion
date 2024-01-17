package controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Categoria;
import models.ProductDTO;
import services.ProductService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategoria(new Categoria());

        if (id > 0) {
            Optional<ProductDTO> product = productService.getById(id);
            if (product.isPresent()) {
                productDTO = product.get();
            }
        }
        req.setAttribute("categorias", productService.listarCategoria());
        req.setAttribute("producto", productDTO);
        req.setAttribute("title", "Productos Form");
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("name");
        Integer precio;
        try {
            precio = Integer.parseInt(req.getParameter("price"));
        } catch (NumberFormatException e) {
            precio = 0;
        }

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        String sku = req.getParameter("sku");
        String fechaStr = req.getParameter("fecha_registro");
        Long categoriaId;

        try {
          categoriaId = Long.valueOf(req.getParameter("categoria"));
        } catch (NumberFormatException e) {
            categoriaId = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("name", "El nombre es requerido.");
        }

        if (sku == null || sku.isBlank()) {
            errores.put("sku", "El sku es requerido.");
        } else if (sku.length() > 10) {
            errores.put("sku", "El sku debe ser menor o igual a 10 caracteres");
        }

        if (fechaStr == null || fechaStr.isBlank()) {
            errores.put("fecha_registro", "La fecha es requerida.");
        }

        if (precio.equals(0)) {
            errores.put("price", "El precio es requerido.");
        }

        if (categoriaId.equals(0L)) {
            errores.put("categoria", "La Categoría es requerida.");
        }

        LocalDate fecha;

        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            fecha = null;
        }

        Categoria categoria = new Categoria();
        categoria.setId(categoriaId);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        productDTO.setName(nombre);
        productDTO.setSku(sku);
        productDTO.setPrice(precio);
        productDTO.setFechaRegistro(fecha);
        productDTO.setCategoria(categoria);

        if (errores.isEmpty()) {
            productService.save(productDTO);

            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", productService.listarCategoria());
            req.setAttribute("producto", productDTO);
            req.setAttribute("title", "Productos form");
            getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
        }
    }
}
