package pl.coderslab.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/app/*",
        "/tasks/*",
        "/events/*",
        "/tasks/*",
        "/venues/*"})
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        if (session.getAttribute("userId") == null) {
            HttpServletResponse response = (HttpServletResponse) resp;
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
//                    "You are not authorized to access this page");
            response.sendRedirect("/login");
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}