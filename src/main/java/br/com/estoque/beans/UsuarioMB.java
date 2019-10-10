package br.com.estoque.beans;

import br.com.estoque.enums.TipoUsuario;

import br.com.estoque.model.Grupo;
import br.com.estoque.model.Usuario;
import br.com.estoque.service.GrupoService;
import br.com.estoque.service.UsuarioService;
import br.com.estoque.util.Transacional;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


@Named
@ViewScoped
public class UsuarioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private GrupoService grupoService;

    private DualListModel<Grupo> grupos;

    private Usuario novoUsuario = new Usuario();

    private List<Grupo> gruposEdicao = new ArrayList<>();

    private List<Usuario> usuarios = new ArrayList<>();

    @PostConstruct
    public void init() {
        List<Grupo> citiesSource = new ArrayList<Grupo>();
        List<Grupo> citiesTarget = new ArrayList<Grupo>();

        citiesSource = grupoService.listarTodos();
        citiesTarget.add (grupoService.busca(2l));


        grupos = new DualListModel<Grupo>(citiesSource , citiesTarget);


    }


    @Transacional
    public void cadastrarUsuario() {
        usuarioService.salvar(novoUsuario);
        this.novoUsuario = new Usuario();
        this.carregaUsuarios();
    }


    public void carregaUsuarios() {
        this.usuarios = usuarioService.listarTodos();
    }


    public void imprimeTemp() {
        System.out.println(this.gruposEdicao);
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


    public DualListModel<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(DualListModel<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<Grupo> getGruposEdicao() {
        return gruposEdicao;
    }

    public void setGruposEdicao(List<Grupo> gruposEdicao) {
        this.gruposEdicao = gruposEdicao;
    }
}
