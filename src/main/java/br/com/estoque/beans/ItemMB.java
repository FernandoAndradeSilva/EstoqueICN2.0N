package br.com.estoque.beans;

import br.com.estoque.model.*;
import br.com.estoque.service.*;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ItemMB implements Serializable {

    private static final long serialVersionUID = 1L;


    //--------------------------INJECTS----------------------------//
    @Inject
    private ItemService itemService;
    @Inject
    private ClasseService classeService;
    @Inject
    private GrupoService grupoService;
    @Inject
    private UnidadeDeMedidaService unidadeDeMedidaService;

    //--------------------------------------------------------------//



    //--------------------------MODELS-----------------------------//
    private Item item = new Item();
    private Classe classe = new Classe();



    private List<Item> itens = new ArrayList<>();

    private List<Classe> classes = new ArrayList<>();


    //--------------------------------------------------------------//



    //----------------------OUTROS ATRIBUTOS------------------------//
    private boolean quantidadeMinima = false;
    private int codFinal;

    //--------------------------------------------------------------//



    //--------------------------MÉTODOS---------------------------//



    //--------------------------ITEM-----------------------------//
    public void configuraPreCodigo() {
        this.item.setCodigo(item.getClasse().getGrupo().getSigla() + item.getClasse().getSigla());
    }

    @Transacional
    public void salvaItem() {
        this.item.configuraCodigo(this.codFinal);
        this.itemService.salvar(this.item);
        this.item = new Item();
        this.quantidadeMinima = false;
        this.codFinal = 0;
        MessageUtil.addMessageTicket
                ("Item cadastrado com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    //-----------------------------------------------------------//






    //--------------------------CLASSES----------------------------//
    @Transacional
    public void salvaClasse() {
        Classe c = classeService.salvaRetorna(classe);
        MessageUtil.addMessageTicket("Adicionado com sucesso", MessageUtil.INFO, MessageUtil.NOREDIRECT);
        List<Classe> classesAtualizadas = classeService.listarPorGrupo(c.getGrupo());
        this.item.getClasse().getGrupo().setClasses(classesAtualizadas);
        this.classe.getGrupo().setClasses(classesAtualizadas);
        this.getClasse().setNome("");
        this.getClasse().setSigla("");

    }

    @Transacional
    public void excluirClasse() {
        Classe c = classe;
        classeService.excluir(classe);
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        List<Classe> classesAtualizadas = classeService.listarPorGrupo(c.getGrupo());
        this.item.getClasse().getGrupo().setClasses(classesAtualizadas);

        if(c.getGrupo() == null) {
            System.out.println("grupo nulo");
        }
        this.classe.getGrupo().setClasses(classesAtualizadas);
        this.getClasse().setNome("");
        this.getClasse().setSigla("");
    }

    @Transacional
    public void editarRowClasse(RowEditEvent event) {
        classeService.salvar((Classe) event.getObject());
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.carregaClasses();
    }

    public void selecionarClasse(Classe c) {
        this.item.setClasse(c);
        this.item.getClasse().getGrupo().setClasses(classeService.listarPorGrupo(c.getGrupo()));

    }

    public void carregaGruposCadastroClasse() {

        if (this.item.getClasse() == null || this.item.getClasse().getGrupo().getId() == 0 ) {
            this.classe.getGrupo().setClasses(classeService.listarTodas());
        } else {
            this.classe.setGrupo(item.getClasse().getGrupo());
            this.classe.getGrupo().setClasses(classeService.listarPorGrupo(this.item.getClasse().getGrupo()));

        }
    }

    public void verificaClassesCadastro() {
        if(this.classe.getGrupo() == null) {

            this.classe = new Classe();
            this.getClasse().getGrupo().setClasses(classeService.listarTodas());
        }
    }

    public void carregaClasses() {
        this.classes = classeService.listarTodas();
    }



    //--------------------------------------------------------------//











    //----------------------UNIDADE DE MEDIDA-----------------------//



    //--------------------------------------------------------------//





    //----------------------MÉTODOS DE BUSCA------------------------//





    //--------------------------------------------------------------//



    //----------------------GETTERS E SETTERS-----------------------//
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }





    public boolean isQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(boolean quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }



    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }



    public int getCodFinal() {
        return codFinal;
    }

    public void setCodFinal(int codFinal) {
        this.codFinal = codFinal;
    }

    //--------------------------------------------------------------//

}
