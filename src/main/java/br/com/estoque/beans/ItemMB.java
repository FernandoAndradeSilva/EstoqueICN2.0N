package br.com.estoque.beans;

import br.com.estoque.model.*;
import br.com.estoque.service.*;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;

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
    private EstoqueService estoqueService;

    //--------------------------------------------------------------//



    //--------------------------MODELS-----------------------------//

    private Item item = new Item();
    private List<Item> itens = new ArrayList<>();

    //--------------------------------------------------------------//



    //----------------------OUTROS ATRIBUTOS------------------------//
    private boolean quantidadeMinima = false;
    private String codFinal;

    //--------------------------------------------------------------//



    //--------------------------MÉTODOS---------------------------//

    @Transacional
    public void salvaItem() {
        item.configuraCodigo(this.codFinal);
        itemService.salvar(this.item);
        estoqueService.salvar(new Estoque(this.item , this.usuarioDaSessao().getSetor().getUnidade()));
        item = new Item();
        quantidadeMinima = false;
        codFinal = "";

        MessageUtil.addMessageTicket
                ("Item cadastrado com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }


    public void configuraPreCodigo() {
        this.item.setCodigo(item.getClasse().getGrupo().getSigla() + item.getClasse().getSigla());
    }

    public Usuario usuarioDaSessao() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }

    //--------------------------------------------------------------//


    //----------------------GETTERS E SETTERS-----------------------//
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    public String getCodFinal() {
        return codFinal;
    }

    public void setCodFinal(String codFinal) {
        this.codFinal = codFinal;
    }

    //--------------------------------------------------------------//

}
