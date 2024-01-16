package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import util.ConectionDBDS;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConectionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try(Connection conn = ConectionDBDS.getConnection()) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                servletRequest.setAttribute("conn", conn);
                filterChain.doFilter(servletRequest, servletResponse);
                conn.commit();
            } catch (SQLException | ServletException e) {
                conn.rollback();
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,  e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
