package br.com.estoque.beans;

import br.com.estoque.model.*;
import br.com.estoque.service.*;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;
import org.primefaces.context.RequestContext;

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


    //-----Injects-----//
    @Inject
    private ItemService itemService;
    @Inject
    private EstoqueService estoqueService;
    @Inject
    private UnidadeDeMedidaService unidadeDeMedidaService;
    @Inject
    private GrupoService grupoService;
    @Inject
    private ClasseService classeService;
      //---------------//


    //-----Models-----//
    private Item item = new Item();
    private List<Item> itens = new ArrayList<>();

    private Estoque estoque;

    private Grupo grupo = new Grupo();
    private List<Grupo> grupos = new ArrayList<>();

    private Classe classe = new Classe();
    private List<Grupo> classes = new ArrayList<>();

    private UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
    private List<UnidadeDeMedida> unidadesDeMedida = new ArrayList<>();
    //----------------//

    //-----Outros-----//
    private boolean quantidadeMinima = false;
    //----------------//

    //-----Grupo-----//

    public void carregaGrupos() {
        this.grupos = grupoService.listarTodos();
    }
    //----------------//


    //-----Classe-----//

    //----------------//


    //-----Item-------//
    @Transacional
    public void salvaItem() {
        this.itemService.salvar(this.item);
    }
    //----------------//


    //-----Un De Medida-------//
    public void carregaUnidadesDeMedida() {
        this.unidadesDeMedida = unidadeDeMedidaService.listarTodos();
    }

    @Transacional
    public void adicionarUnDeMedida() {
        this.unidadeDeMedidaService.salvar(unidadeDeMedida);
        MessageUtil.addMessageTicket("Adicionado com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.unidadeDeMedida = new UnidadeDeMedida();
        this.carregaUnidadesDeMedida();

    }

    @Transacional
    public String excluirUnDeMedida() {

        System.out.println(this.item.getClasse());

        this.unidadeDeMedidaService.excluir(unidadeDeMedida);
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("PF('dlgCadastroUnidadeDeMedida').hide();");
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.unidadeDeMedida = new UnidadeDeMedida();

        return "";


    }


    //-----------------------//



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

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Grupo> getClasses() {
        return classes;
    }

    public void setClasses(List<Grupo> classes) {
        this.classes = classes;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    public List<UnidadeDeMedida> getUnidadesDeMedida() {
        return unidadesDeMedida;
    }

    public void setUnidadesDeMedida(List<UnidadeDeMedida> unidadesDeMedida) {
        this.unidadesDeMedida = unidadesDeMedida;
    }
}
