package br.com.estoque.beans;

import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;
import br.com.estoque.model.Item;
import br.com.estoque.model.UnidadeDeMedida;
import br.com.estoque.service.ClasseService;
import br.com.estoque.service.GrupoService;
import br.com.estoque.service.ItemService;
import br.com.estoque.service.UnidadeDeMedidaService;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;

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

    //------------------INJECTS-------------------//
    @Inject
    private ItemService itemService;
    @Inject
    private GrupoService grupoService;
    @Inject
    private ClasseService classeService;
    @Inject
    private UnidadeDeMedidaService unidadeDeMedidaService;

    //--------------------------------------------//


    //------------------MODELS-------------------//
    private Item item = new Item();
    private Grupo grupo = new Grupo();
    private Classe classe = new Classe();
    private UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();

    private List<Item> itens = new ArrayList<Item>();
    private List<Grupo> grupos = new ArrayList<Grupo>();
    private List<Classe> classes = new ArrayList<Classe>();
    private List<UnidadeDeMedida> unidadesDeMedida = new ArrayList<UnidadeDeMedida>();

    //-------------------------------------------//


    //--------------OOUTROS ATRIBUTOS------------//
    private boolean quantidadeMinima = false;
    private int codFinal;

    //-------------------------------------------//


    //--------------MÉTODOS ITEM----------------//
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


    /**
     * Configura início do código gerado automaticamente
     */
    public void configuraPreCodigo() {
        this.item.setCodigo(item.getClasse().getGrupo().getSigla() + item.getClasse().getSigla());
    }

    //-------------------------------------------//

    //-------------GETTERS E SETTERS-------------//


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    public UnidadeDeMedida getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
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

    public boolean isQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(boolean quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public int getCodFinal() {
        return codFinal;
    }

    public void setCodFinal(int codFinal) {
        this.codFinal = codFinal;
    }
}
