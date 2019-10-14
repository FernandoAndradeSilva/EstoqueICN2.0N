package br.com.estoque.security;

import br.com.estoque.enums.TipoUsuario;
import br.com.estoque.model.Usuario;
import br.com.estoque.service.UsuarioService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class LogonMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioService usuarioService;

    private Usuario usuarioLogado = null;


    public String logar() {
       usuarioLogado = usuarioService.buscar(1l);

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("usuario", usuarioLogado);

        String pagina = (String) externalContext.getSessionMap().get("pagina");

        if (pagina != null) {
            return pagina;
        } else
            return "pages/index.xhtml?faces-redirect=true";


    }


    public void redireciona() {

        if(this.usuarioLogado != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("http://localhost:8080/ProjetoEstoque_2_0_war_exploded/pages/index.xhtml");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
