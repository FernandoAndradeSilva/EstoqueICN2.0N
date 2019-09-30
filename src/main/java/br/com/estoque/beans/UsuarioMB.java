package br.com.estoque.beans;

import br.com.estoque.model.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@SessionScoped
public class UsuarioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario = null;
    private List<Usuario> users = new ArrayList<>();
    private boolean teste = false;
    private String campoPesquisa;

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


    }

//
//    public void pesquisa() {
//
//        List<Categoria> categoriasTemp = new ArrayList<>();
//
//        for(Categoria categoria : this.categorias) {
//            if(categoria.getNome().equals(this.campoPesquisa)) {
//                categoriasTemp.add(categoria);
//                break;
//            }
//        }
//
//        categorias = categoriasTemp;
//    }





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

    public String getCampoPesquisa() {
        return campoPesquisa;
    }

    public void setCampoPesquisa(String campoPesquisa) {
        this.campoPesquisa = campoPesquisa;
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


    public List<Usuario> getUsers() {
        return users;
    }



    public void setUsers(List<Usuario> users) {
        this.users = users;
    }
}
