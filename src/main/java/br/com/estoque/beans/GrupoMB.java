package br.com.estoque.beans;


import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;
import br.com.estoque.service.GrupoService;
import br.com.estoque.service.ClasseService;
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
public class GrupoMB implements Serializable {

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

    @Transacional
    public void excluirGrupo() {
        grupoService.excluir(novoGrupo);
        this.carregaGrupos();
        MessageUtil.addMessageTicket("Excluído com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    public void selecionaGrupo(Grupo grupo) {
        this.grupo = grupo;
        if(this.campoBuscaClasse != "") {
            this.campoBuscaClasse = "";
        }
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
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    @Transacional
    public void excluirClasse(Classe classe) {
        classeService.excluir(classe);
        MessageUtil.addMessageTicket("Excluído com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    public void atualizaExclusao() {
        Grupo cat = grupoService.busca(this.getGrupo().getId());
        this.getGrupo().setClasses(cat.getClasses());
        this.classe = new Classe();
    }

    @Transacional
    public void adicionaNovaClasse() {
        classe.setGrupo(this.grupo);
        classeService.salvar(classe);
        Grupo cat = grupoService.busca(this.getGrupo().getId());
        this.getGrupo().setClasses(cat.getClasses());
        this.classe = new Classe();
        MessageUtil.addMessageTicket("Cadastrado com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    public void buscaManualClasse(int tipoCampoPesquisa) {

        Grupo cat = grupoService.busca(this.getGrupo().getId());
        this.getGrupo().setClasses(cat.getClasses());

        List<Classe> listResult = new ArrayList<>();

        for (Classe classe : this.getGrupo().getClasses()) {

            if(tipoCampoPesquisa == 1) {
                if(classe.getSigla().equalsIgnoreCase(this.campoBuscaClasse)) {
                    listResult.add(classe);
                }
            } else if(tipoCampoPesquisa == 2) {
                if(classe.getNome().equalsIgnoreCase(this.campoBuscaClasse)) {
                    listResult.add(classe);
                }
            }
        }
        if(listResult.size() >0) {
            this.getGrupo().setClasses(listResult);
        }



    }

    public void buscaManualGrupo(int tipoCampoPesquisa) {

        this.grupos = grupoService.listarTodos();
        List<Grupo> listResult = new ArrayList<>();


        System.out.println(this.campoBuscaGrupo);

        for (Grupo grupo : this.grupos) {

            if(tipoCampoPesquisa == 1) {
                if(grupo.getSigla().equalsIgnoreCase(this.campoBuscaGrupo)) {
                    listResult.add(grupo);
                }
            } else if(tipoCampoPesquisa == 2) {
                if(grupo.getNome().equalsIgnoreCase(this.campoBuscaGrupo)) {
                    listResult.add(grupo);
                }
            }
        }
        if(listResult.size() >0) {
            this.setGrupos(listResult);
        }


    }

    public void recarregarBusca() {
            classe.setGrupo(this.grupo);
            Grupo cat = grupoService.busca(this.getGrupo().getId());
            this.getGrupo().setClasses(cat.getClasses());
            this.classe = new Classe();
    }

    public void carregaGrupos() {
        grupos = grupoService.listarTodos();
    }

    public void carregaClassesPorGrupo() {

        Grupo grupo1 = grupoService.busca(1l);
        List<Classe> classes = classeService.listarPorGrupo(grupo1);

        System.out.println(classes);



    }

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

    //    public void pesquisarGrupos() {
//
//        this.carregaGrupos();
//        List<Grupo> grupuPesquisada = new ArrayList<>();
//
//        for (Grupo grupo : this.grupos) {
//            if(grupo.getNome().equalsIgnoreCase(campoPesquisaGrupo)) {
//                grupuPesquisada.add(grupo);
//                break;
//            }
//        }
//        if(grupuPesquisada.size() == 1) {
//            this.grupos = grupuPesquisada;
//        }
//    }
//
//
//    public void pesquisarClasses() {
//
//        this.atualizaPesquisaClasse();
//        List<Classe> tipoPesquisado = new ArrayList<>();
//
//        for (Classe classe : this.grupuSelecionada.getClasses()) {
//            if(classe.getNome().equalsIgnoreCase(campoPesquisaClasse)) {
//                tipoPesquisado.add(classe);
//                break;
//            }
//        }
//        if(tipoPesquisado.size() == 1) {
//            this.grupuSelecionada.setClasses(tipoPesquisado);
//        }
//    }
}
