package fr.insee.rmes.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaticFilter implements javax.servlet.Filter {
    private RequestDispatcher defaultRequestDispatcher;

    @Override
    public void destroy() {}

    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURI().endsWith("/swagger-ui")) {
            resp.sendRedirect(req.getRequestURI() + "/index.html");
        }
        else if (req.getRequestURI().endsWith("/swagger-ui/")) {
            resp.sendRedirect(req.getRequestURI() + "index.html");
        }
        else {
            defaultRequestDispatcher.forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.defaultRequestDispatcher = filterConfig.getServletContext().getNamedDispatcher("default");
        new Configuration();
    }

}
