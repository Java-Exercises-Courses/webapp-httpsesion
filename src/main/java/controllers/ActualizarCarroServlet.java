package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Carro;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/carro/actualizar")
public class ActualizarCarroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Carro carro = (Carro) session.getAttribute("carro");
        updateCantidades(req,carro);
        updateProductos(req, carro);

        resp.sendRedirect(req.getContextPath() +  "/carro/ver");
    }

    private void updateProductos(HttpServletRequest request, Carro carro) {
        String[] deleteIds = request.getParameterValues("deleteProductos");

        if (deleteIds != null && deleteIds.length > 0) {
            List<String> productIds = Arrays.asList(deleteIds);
            carro.removeProductos(productIds);
        }
    }

    private void updateCantidades(HttpServletRequest request, Carro carro) {
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            if (paramName.startsWith("cant_")) {
                String id = paramName.substring(5);
                String cantidad = request.getParameter(paramName);
                if ( cantidad != null) {
                    carro.updateCantidad(id, Integer.parseInt(cantidad));
                }
            }
        }
    }
}
