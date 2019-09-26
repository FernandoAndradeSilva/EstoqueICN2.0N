package br.com.estoque.util;

import br.com.estoque.beans.UsuarioMB;
import br.com.estoque.model.Usuario;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;




@WebFilter(urlPatterns = { "/pages/*" })
public class FilterAutenticacao implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) // em todas requisições
            throws IOException, ServletException {

                HttpServletRequest req = (HttpServletRequest) request;
                HttpSession session = req.getSession();
                Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");



            if(usuarioLogado != null) {
                chain.doFilter(request, response);
           } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.xhtml");
                dispatcher.forward(request, response);
           }






    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { // quando sobe o servidor

    }

    @Override
    public void destroy() {

    }



}
