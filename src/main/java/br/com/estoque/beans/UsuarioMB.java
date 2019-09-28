package br.com.estoque.beans;

import br.com.estoque.model.Categoria;
import br.com.estoque.model.Usuario;
import br.com.estoque.service.CategoriaService;
import br.com.estoque.util.Transacional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.omnifaces.util.Faces.getRequestURI;


@Named
@SessionScoped
public class UsuarioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario = null;

    @Inject
    private CategoriaService categoriaService;

    private List<Usuario> users = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();

    private Categoria categoriaSelecionada = null;
    private Categoria novaCategoria = null;
    private boolean teste = false;



    public void preCadastroNovaCategoria() {

        this.novaCategoria = new Categoria();

    }

    @PostConstruct
    public void carrega() {

        System.out.println("Criando users");

        List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario = new Usuario("Fernando" , "fernando@bol.com" , "1234" , 1 , true);
        users.add(usuario);
        usuario = new Usuario("Fernando" , "fernando@bol.com" , "1234" , 1 , true);
        users.add(usuario);
        usuario = new Usuario("Fernando" , "fernando@bol.com" , "1234" , 1 , true);
        users.add(usuario);
        usuario = new Usuario("Fernando" , "fernando@bol.com" , "1234" , 1 , true);
        users.add(usuario);

        this.categorias = categoriaService.listar();
    }


    @Transacional
    public void salvaPrimeiraCategoria() {

        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        categoriaService.salvar(categoria);

    }



    public String carregaUsuario() {

        usuario = new Usuario();
        usuario.setNome("Fernando");
        usuario.setEmail("fernando@bol.com");
        usuario.setSenha("12345");

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getSessionMap().put("usuario", usuario);

        String pagina = (String) externalContext.getSessionMap().get("pagina");
        System.out.println(pagina);

        if (pagina != null) {
            return pagina;
        } else
            return "pages/index.xhtml?faces-redirect=true";


    }


    public Categoria getNovaCategoria() {
        return novaCategoria;
    }

    public void setNovaCategoria(Categoria novaCategoria) {
        this.novaCategoria = novaCategoria;
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

    public Categoria getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public List<Usuario> getUsers() {
        return users;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
    }
}
