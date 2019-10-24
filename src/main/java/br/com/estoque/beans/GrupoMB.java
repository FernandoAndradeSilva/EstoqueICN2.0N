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
public class GrupoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    //--------------------------INJECTS----------------------------//

    @Inject
    private GrupoService grupoService;

    @Inject
    private ClasseService classeService;

    //--------------------------------------------------------------//



    //--------------------------MODELS-----------------------------//

    private Grupo grupo = new Grupo();
    private Classe classe = new Classe();

    private List<Grupo> grupos = new ArrayList<>();

    //--------------------------------------------------------------//


    //----------------------OUTROS ATRIBUTOS------------------------//




    //--------------------------------------------------------------//



    //--------------------------MÉTODOS---------------------------//


    @Transacional
    public void salva() {
        Grupo g = grupoService.salvaRetorna(grupo);
        MessageUtil.addMessageTicket("Adicionado com sucesso", MessageUtil.INFO, MessageUtil.NOREDIRECT);
        this.grupo = new Grupo();
        this.carregaGrupos();
    }

    @Transacional
    public void excluir() {
        grupoService.excluir(grupo);
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.grupo = new Grupo();
        this.carregaGrupos();
    }

    @Transacional
    public void editarRow(RowEditEvent event) {
        grupoService.salvar((Grupo) event.getObject());
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.carregaGrupos();
    }

    @Transacional
    public void excluirClasseGrupo() {
        Classe c = this.classe;
        classeService.excluir(classe);
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.selecionaGrupoToggle(c.getGrupo());
        this.classe = new Classe();
    }

    @Transacional
    public void salvaClasseGrupo() {
        classe.setGrupo(grupo);
        Classe c = classeService.salvaRetorna(classe);
        MessageUtil.addMessageTicket("Adicionado com sucesso", MessageUtil.INFO, MessageUtil.NOREDIRECT);
        this.selecionaGrupoToggle(c.getGrupo());
        this.classe = new Classe();

    }


    public void carregaGrupos() {
        this.grupos = grupoService.listar();
    }


    public void selecionaGrupoToggle(Grupo grupo) {
        this.setGrupo(grupo);
        grupo.setClasses(classeService.listarPorGrupo(grupo));
    }

    public void resetaGrupo() {
        this.setGrupo(new Grupo());
    }

    //--------------------------------------------------------------//



    //---------------------GETTERS E SETTERS------------------------//


    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
