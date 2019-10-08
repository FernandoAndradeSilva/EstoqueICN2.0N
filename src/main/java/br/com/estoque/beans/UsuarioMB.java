package br.com.estoque.beans;

import br.com.estoque.enums.TipoUsuario;
import br.com.estoque.model.Usuario;
import br.com.estoque.service.UsuarioService;
import br.com.estoque.util.Transacional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


@Named
@SessionScoped
public class UsuarioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioService usuarioService;

    private Usuario usuario = null;

    private Usuario novoUsuario = new Usuario();

    private List<Usuario> usuarios = new ArrayList<>();

    @Transacional
    public void cadastrarUsuario() {
        usuarioService.salvar(novoUsuario);
        this.novoUsuario = new Usuario();
        this.carregaUsuarios();
    }

    @PostConstruct
    public void carregaUsuarios() {
        this.usuarios = usuarioService.listarTodos();
    }



    public String carregaUsuario() {

        usuario = new Usuario();
        usuario.setNome("Fernando");
        usuario.setEmail("fernando@bol.com");
        usuario.setSenha("12345");
        usuario.setUsuario("nando");
        usuario.setTipoUsuario(TipoUsuario.MASTER);

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("usuario", usuario);

        String pagina = (String) externalContext.getSessionMap().get("pagina");


        if (pagina != null) {
            return pagina;
        } else
            return "pages/index.xhtml?faces-redirect=true";


    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public EnumSet<TipoUsuario> tiposUsuario() {
        return EnumSet.allOf(TipoUsuario.class);
    }

    public Usuario getNovoUsuario() {
        return novoUsuario;
    }

    public void setNovoUsuario(Usuario novoUsuario) {
        this.novoUsuario = novoUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



}
