package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import services.ServiceException;
import java.io.IOException;

@WebFilter("/*")
public class ConectionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (ServiceException e) {
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,  e.getMessage());
                e.printStackTrace();
            }
    }
}
