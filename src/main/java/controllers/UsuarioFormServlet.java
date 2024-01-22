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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/form")
public class UsuarioFormServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Usuario usuario = new Usuario();

        if (id > 0) {
            Optional<Usuario> user = userService.getById(id);
            if (user.isPresent()) {
                usuario = user.get();
            }
        }

        req.setAttribute("user", usuario);
        req.setAttribute("title", "Users Form");
        getServletContext().getRequestDispatcher("/form-usuarios.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Map<String, String> errores = new HashMap<>();
        if (username == null || username.isBlank()) {
            errores.put("username", "El username es requerido");
        }

        if (email == null || email.isBlank()) {
            errores.put("email", "El email es requerido");
        }

        if (password == null || password.isBlank()) {
            errores.put("password", "El password es requerido");
        }

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEmail(email);

        if (errores.isEmpty()) {
            userService.save(usuario);
            resp.sendRedirect(req.getContextPath() + "/usuarios");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("user", usuario);
            req.setAttribute("title", "Users Form");
            getServletContext().getRequestDispatcher("/form-usuarios.jsp").forward(req, resp);
        }
    }
}
