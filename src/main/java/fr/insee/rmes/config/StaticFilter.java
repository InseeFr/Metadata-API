package fr.insee.rmes.config;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StaticFilter implements jakarta.servlet.Filter {
    private RequestDispatcher defaultRequestDispatcher;
    private static Logger logger = LogManager.getLogger(StaticFilter.class);



    @Override
    public void destroy() {
        logger.warn("StaticFilter is destroyed");
    }

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
        else if (req.getRequestURI().endsWith("/")) {
            resp.sendRedirect(req.getRequestURI() + "swagger-ui/index.html");
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
