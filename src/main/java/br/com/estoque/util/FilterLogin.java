package br.com.estoque.util;

import br.com.estoque.model.Usuario;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/login.xhtml"})
public class FilterLogin implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) // em todas requisições
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");


        if(usuarioLogado != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/index.xhtml?faces-redirect=true");
            dispatcher.forward(request, response);
        } else
            chain.doFilter(request, response);
    }




    @Override
    public void init(FilterConfig filterConfig) throws ServletException { // quando sobe o servidor

    }

    @Override
    public void destroy() {

    }


}
