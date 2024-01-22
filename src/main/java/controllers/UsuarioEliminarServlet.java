package controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.entities.Usuario;
import services.UserService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/usuarios/eliminar")
public class UsuarioEliminarServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            id = 0L;
        }

        if (id > 0) {
            Optional<Usuario> u = userService.getById(id);
            if (u.isPresent()) {
                userService.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/usuarios");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el usuario");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El id del usuario es necesario");
        }
    }
}
