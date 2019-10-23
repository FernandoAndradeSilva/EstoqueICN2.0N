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
public class ClasseMB implements Serializable {

    private static final long serialVersionUID = 1L;

    //--------------------------INJECTS----------------------------//

    @Inject
    private ClasseService classeService;
    @Inject
    private GrupoService grupoService;

    //--------------------------------------------------------------//




    //--------------------------MODELS-----------------------------//

    private Classe classe = new Classe();
    private List<Classe> classes = new ArrayList<>();


    //--------------------------------------------------------------//



    //----------------------OUTROS ATRIBUTOS------------------------//




    //--------------------------------------------------------------//



    //--------------------------MÉTODOS----------------------------//

    @Transacional
    public void salva() {
        Classe c = classeService.salvaRetorna(classe);
        MessageUtil.addMessageTicket("Adicionado com sucesso", MessageUtil.INFO, MessageUtil.NOREDIRECT);
        this.listarClasses(c.getGrupo());
        this.classe.setSigla("");
        this.classe.setNome("");
    }

    @Transacional
    public void excluir() {
        Classe c = classe;
        classeService.excluir(classe);
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.listarClasses(c.getGrupo());
        this.classe.setSigla("");
        this.classe.setNome("");
    }

    @Transacional
    public void editarRow(RowEditEvent event) {
        Classe c = classeService.salvaRetorna((Classe) event.getObject());
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.listarClasses(c.getGrupo());
    }

    public void listarClasses(Grupo grupo) {
        this.classes = classeService.listarPorGrupo(grupo);
    }

    public void verificaGrupoPreenchido(Grupo grupo) {
        if(grupo == null || grupo.getId() == 0) {
            classe.setGrupo(new Grupo());
            this.listarClasses(classe.getGrupo());
        }

    }


    //-------------------------------------------------------------//



    //--------------GETTERS E SETTERS-------------------------//
    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classe> getClasses(Grupo grupo) {
        return classes;
    }



    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public List<Classe> getClasses() {
        return classes;
    }


}

