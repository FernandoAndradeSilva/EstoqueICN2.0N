package br.com.estoque.beans;

import br.com.estoque.enums.TipoUsuario;

import br.com.estoque.model.Grupo;
import br.com.estoque.model.Usuario;
import br.com.estoque.service.GrupoService;
import br.com.estoque.service.GrupoUsuarioService;
import br.com.estoque.service.UsuarioService;
import br.com.estoque.util.Transacional;
import org.primefaces.event.TransferEvent;
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
    private GrupoUsuarioService grupoUsuarioService;

    private Usuario usuarioSelecionado = new Usuario();

    private DualListModel<Grupo> grupos = new DualListModel<>();

    private Usuario novoUsuario = new Usuario();

    private List<Usuario> usuarios = new ArrayList<>();


    public void carregaGruposUsuario() {


        grupos = new DualListModel<Grupo>(grupoUsuarioService.gruposNaoAssociados(this.usuarioSelecionado.getId()),
                grupoUsuarioService.gruposAssociados(this.usuarioSelecionado.getId()));



    }


    public void atualizaGrupos() {

        System.out.println("Entrando no método");

        List<Grupo> gruposDoUsuario = grupoUsuarioService.gruposAssociados(usuarioSelecionado.getId());
        List<Grupo> gruposFinais = grupos.getTarget();

        for (Grupo grupo : gruposFinais) {

            if(!gruposDoUsuario.contains(grupo)) {
                System.out.println("Não conteem" + grupo);
            }


        }


    }


    public void onTransfer(TransferEvent event) {
        System.out.println(event.getItems());
        System.out.println(event.getSource());
        System.out.println(event.isAdd());

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

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
}
