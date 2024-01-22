package controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.entities.Usuario;
import services.LoginService;
import services.UserService;
import java.io.IOException;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> usernameOptional = loginService.getUsername(req);

        if (usernameOptional.isPresent()) {
            req.setAttribute("title", "Hola " + usernameOptional.get());
            getServletContext().getRequestDispatcher("/success-login.jsp").forward(req, resp);
        } else {
            req.setAttribute("title", "Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<Usuario> usuarioOptional = userService.getByUsername(username, password);

        if (usuarioOptional.isPresent()) {
            HttpSession session = req.getSession();

            session.setAttribute("username", usuarioOptional.get().getUsername());

            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, no está autorizado");
        }
    }
}
