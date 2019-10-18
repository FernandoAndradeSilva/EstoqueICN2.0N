package br.com.estoque.beans;



import br.com.estoque.model.Classe;
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
public class ClasseMB implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------INJECTS---------------//
    @Inject
    private ClasseService classeService;
    //---------FIM INJECTS-----------//


    //------------MODELS-------------//
    private Classe classe = new Classe();
    private List<Classe> classes = new ArrayList<>();
    //---------FIM MODELS-----------//


    //-----------OUTROS-------------//



    //---------FIM OUTROS-----------//


    //-----------MÉTODOS TRANSCIONAIS -----------//
    @Transacional
    public void adicionar() {
        classeService.salvar(classe);
        MessageUtil.addMessageTicket("Adicionado com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.classe = new Classe();
        this.carregaClasses();

    }

    @Transacional
    public void excluir() {
        classeService.excluir(classe);
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.classe = new Classe();
        this.carregaClasses();
    }

    @Transacional
    public void editarRow(RowEditEvent event) {
        classeService.salvar(((Classe) event.getObject()));
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.carregaClasses();
    }

    //----------FIM MÉTODOS TRANSCIONAIS----------//
    //---------------------------------------//
    ////////////////// OUTROS /////////////////
    public void carregaClasses() {
        this.classes = classeService.listarTodas();
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }


    ///////////////FIM OUTROS /////////////////



}

