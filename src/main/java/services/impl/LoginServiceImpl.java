package services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import services.LoginService;

import java.util.Optional;

@ApplicationScoped
public class LoginServiceImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        if(username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
