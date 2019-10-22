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
    private Grupo grupo = new Grupo();
    private UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();

    private List<Item> itens = new ArrayList<>();
    private List<Grupo> grupos = new ArrayList<>();
    private List<Classe> classes = new ArrayList<>();
    private List<UnidadeDeMedida> unidadesDeMedida = new ArrayList<>();

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



    //--------------------------GRUPO----------------------------//
    @Transacional
    public void salvaGrupo() {
        Grupo g = grupoService.salvaRetorna(grupo);
        MessageUtil.addMessageTicket("Adicionado com sucesso", MessageUtil.INFO, MessageUtil.NOREDIRECT);
        this.grupo = new Grupo();
        this.carregaGrupos();
    }

    @Transacional
    public void excluirGrupo() {
        grupoService.excluir(grupo);
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.grupo = new Grupo();
        this.carregaGrupos();
    }

    @Transacional
    public void editarRowGrupo(RowEditEvent event) {
        grupoService.salvar(((Grupo) event.getObject()));
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.carregaGrupos();
    }

    public void carregaGrupos() {
        this.grupos = grupoService.listarTodos();
    }

    //--------------------------------------------------------------//



    //----------------------MÉTODOS DE BUSCA------------------------//
    public void carregaClasses() {
        this.classes = classeService.listarTodas();
    }

    public void carregaUnidadesDeMedida() {
        this.unidadesDeMedida = unidadeDeMedidaService.listarTodos();
    }


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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public UnidadeDeMedida getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
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

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public List<UnidadeDeMedida> getUnidadesDeMedida() {
        return unidadesDeMedida;
    }

    public void setUnidadesDeMedida(List<UnidadeDeMedida> unidadesDeMedida) {
        this.unidadesDeMedida = unidadesDeMedida;
    }

    public int getCodFinal() {
        return codFinal;
    }

    public void setCodFinal(int codFinal) {
        this.codFinal = codFinal;
    }

    //--------------------------------------------------------------//

}
