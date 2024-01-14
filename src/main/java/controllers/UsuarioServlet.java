package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Usuario;
import services.LoginService;
import services.UserService;
import services.impl.LoginServiceImpl;
import services.impl.UserServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        UserService userService = new UserServiceImpl(conn);
        List<Usuario> usuarios = userService.getUsers();

        LoginService loginService = new LoginServiceImpl();
        Optional<String> usernameOptional = loginService.getUsername(req);

        req.setAttribute("users", usuarios);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("title", "Listado de usuarios");
        getServletContext().getRequestDispatcher("/listar-usuarios.jsp").forward(req, resp);
    }
}
