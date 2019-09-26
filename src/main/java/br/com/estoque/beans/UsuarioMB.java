package br.com.estoque.beans;

import br.com.estoque.model.Usuario;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;


@Named
@SessionScoped
public class UsuarioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario = null;




    private boolean teste = false;



    public void carregaUsuario() {

        usuario = new Usuario();
        usuario.setNome("Fernando");
        usuario.setEmail("fernando@bol.com");
        usuario.setSenha("12345");

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("usuario" , usuario);

        System.out.println("Carregou");

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public boolean isTeste() {
        return teste;
    }

    public void setTeste(boolean teste) {
        this.teste = teste;
    }
}
