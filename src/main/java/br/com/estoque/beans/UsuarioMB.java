package br.com.estoque.beans;

import br.com.estoque.enums.TipoUsuario;

import br.com.estoque.model.Grupo;
import br.com.estoque.model.GrupoUsuario;
import br.com.estoque.model.Usuario;
import br.com.estoque.service.GrupoUsuarioService;
import br.com.estoque.service.SetorService;
import br.com.estoque.service.UsuarioService;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

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

    //--------------------------INJECTS----------------------------//
    @Inject
    private UsuarioService usuarioService;
    @Inject
    private GrupoUsuarioService grupoUsuarioService;

    //--------------------------------------------------------------//



    //--------------------------MODELS-----------------------------//
    private Usuario usuarioSelecionado = new Usuario();
    private Usuario novoUsuario = new Usuario();
    private List<Usuario> usuarios = new ArrayList<>();

    //--------------------------------------------------------------//



    //----------------------OUTROS ATRIBUTOS------------------------//
    private DualListModel<Grupo> grupos = new DualListModel<>();

    //--------------------------------------------------------------//




    //--------------------------MÉTODOS----------------------------//

    @Transacional
    public void salvar() {
        usuarioService.salvar(novoUsuario);
        this.novoUsuario = new Usuario();
        this.carregaUsuarios();
    }

    @Transacional
    public void editarRow(RowEditEvent event) {
        usuarioService.salvar(((Usuario) event.getObject()));
        MessageUtil.addMessageTicket("Informações alteradas com sucesso", MessageUtil.INFO, MessageUtil.NOREDIRECT);

    }

    @Transacional
    public void atualizaGrupos() {
        List<Grupo> gruposDoUsuario = grupoUsuarioService.gruposAssociados(usuarioSelecionado.getId());
        List<Grupo> gruposFinais = grupos.getTarget();

        for (Grupo grupo : gruposFinais) { // ADICIONA AS QUE USUÁRIO NÃO TEM
            if (!gruposDoUsuario.contains(grupo)) {
                GrupoUsuario gp = new GrupoUsuario();
                gp.getId().setUsuario(this.usuarioSelecionado);
                gp.getId().setGrupo(grupo);
                grupoUsuarioService.salvar(gp);
            }
        }

        for (Grupo grupo : gruposDoUsuario) { // REMOVE OS QUE FORAM TIRADOS
            if (!gruposFinais.contains(grupo)) {
                GrupoUsuario gp = new GrupoUsuario();
                gp.getId().setUsuario(this.usuarioSelecionado);
                gp.getId().setGrupo(grupo);
                grupoUsuarioService.excluir(gp);
            }
        }
    }

    public void carregaGruposUsuario() {
        grupos = new DualListModel<Grupo>(grupoUsuarioService.gruposNaoAssociados(this.usuarioSelecionado.getId()),
                grupoUsuarioService.gruposAssociados(this.usuarioSelecionado.getId()));

    }

    public void carregaUsuarios() {
        this.usuarios = usuarioService.listar();
    }

    //---------------------------------------------------------//



    //--------------GETTERS E SETTERS-------------------------//
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
