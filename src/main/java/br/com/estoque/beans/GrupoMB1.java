package br.com.estoque.beans;


import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;
import br.com.estoque.service.ClasseService;
import br.com.estoque.service.GrupoService;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;
import org.primefaces.event.RowEditEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class GrupoMB1 implements Serializable {

    private static final long serialVersionUID = 1L;


    @Inject
    private GrupoService grupoService;

    @Inject
    private ClasseService classeService;

    private Grupo grupo = new Grupo();
    private Grupo novoGrupo = new Grupo();
    private Classe classe = new Classe();

    private String campoBuscaClasse;
    private String campoBuscaGrupo;

    private List<Grupo> grupos = new ArrayList<>();
    private List<Classe> tiposFiltrados;


    ////////// MÉTODOS TRANSCIONAIS /////////////
    @Transacional
    public void salvarGrupo() {
        this.grupoService.salvar(novoGrupo);
        this.novoGrupo = new Grupo();
        this.carregaGrupos();
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    @Transacional
    public void salvarClasse() {
        this.classeService.salvar(classe);
        this.classe = new Classe();
        this.carregaGrupos();
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    @Transacional
    public void editarGrupo(RowEditEvent event) {
        grupoService.salvar(((Grupo) event.getObject()));
        MessageUtil.addMessageTicket("Informações alteradas com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);

    }

    @Transacional
    public void editarClasse(RowEditEvent event) {
        classeService.salvar(((Classe) event.getObject()));
        MessageUtil.addMessageTicket("Informações alteradas com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);

    }

    @Transacional
    public void excluirGrupo() {
        grupoService.excluir(novoGrupo);
        this.carregaGrupos();
        MessageUtil.addMessageTicket("Excluído com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    @Transacional
    public void excluirClasse(Classe classe) {
        classeService.excluir(classe);
        MessageUtil.addMessageTicket("Excluído com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

//    @Transacional
//    public void adicionaNovaClasse() {
//        classe.setGrupo(this.grupo);
//        classeService.salvar(classe);
//        Grupo cat = grupoService.buscaPorId(this.getGrupo().getId());
//        this.getGrupo().setClasses(cat.getClasses());
//        this.classe = new Classe();
//        MessageUtil.addMessageTicket("Cadastrado com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);    }

    /////////FIM MÉTODOS TRANSCIONAIS //////////

    ////////////////// OUTROS /////////////////
    public void carregaGrupos() {
        grupos = grupoService.listar();
        this.campoBuscaGrupo = "";
    }

    public void selecionaGrupo(Grupo grupo) {
        this.grupo = grupo;
        if(this.campoBuscaClasse != "") {
            this.campoBuscaClasse = "";
            this.carregaGrupos();
        }
    }
//
//    public void atualizaExclusao() {
//        Grupo cat = grupoService.buscaPorId(this.getGrupo().getId());
//        this.getGrupo().setClasses(cat.getClasses());
//        this.classe = new Classe();
//    }
//
//    public void recarregarBusca() {
//        classe.setGrupo(this.grupo);
//        Grupo cat = grupoService.buscaPorId(this.getGrupo().getId());
//        this.getGrupo().setClasses(cat.getClasses());
//        this.classe = new Classe();
//        this.campoBuscaClasse = "";
//    }





    //////////FIM MÉTODOS DE BUSCA //////////////

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }


    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }


    public Grupo getNovoGrupo() {
        return novoGrupo;
    }

    public String getCampoBuscaClasse() {
        return campoBuscaClasse;
    }

    public void setCampoBuscaClasse(String campoBuscaClasse) {
        this.campoBuscaClasse = campoBuscaClasse;
    }

    public void setNovoGrupo(Grupo novoGrupo) {
        this.novoGrupo = novoGrupo;
    }

    public List<Classe> getClassesFiltrados() {
        return tiposFiltrados;
    }

    public void setClassesFiltrados(List<Classe> tiposFiltrados) {
        this.tiposFiltrados = tiposFiltrados;
    }

    public String getCampoBuscaGrupo() {
        return campoBuscaGrupo;
    }

    public void setCampoBuscaGrupo(String campoBuscaGrupo) {
        this.campoBuscaGrupo = campoBuscaGrupo;
    }


}
