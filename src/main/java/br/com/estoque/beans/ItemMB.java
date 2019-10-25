package br.com.estoque.beans;

import br.com.estoque.model.*;
import br.com.estoque.security.LogonMB;
import br.com.estoque.service.*;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Inject
    private UnidadeService unidadeService;

    //--------------------------------------------------------------//



    //--------------------------MODELS-----------------------------//
    private Item item = new Item();
    private List<Estoque> itens = new ArrayList<>();

    private List<Object> itensHash = new ArrayList<>();
    //--------------------------------------------------------------//



    //----------------------OUTROS ATRIBUTOS------------------------//
    private boolean quantidadeMinima = false;
    private String codFinal;

    //--------------------------------------------------------------//



    //--------------------------MÉTODOS---------------------------//
    @Transacional
    public void salva() {
        item.configuraCodigo(this.codFinal);
        itemService.salvar(this.item);
        estoqueService.salvar(new Estoque(this.item , LogonMB.usuarioDaSessao().getSetor().getUnidade()));
        item = new Item();
        quantidadeMinima = false;
        codFinal = "";

        MessageUtil.addMessageTicket
                ("Item cadastrado com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    @Transacional
    public void movimentar(Estoque estoqueItem , int tipo) {
        estoqueItem.atualizaSaldo(50 , tipo);
        estoqueService.atualizar(estoqueItem);
    }


    public void configuraPreCodigo() {
        this.item.setCodigo(item.getClasse().getGrupo().getSigla() + item.getClasse().getSigla());
    }

    public void carregaItens() {
        Unidade unidade = unidadeService.buscar(LogonMB.usuarioDaSessao().getSetor().getUnidade());
        itens = unidade.getEstoques();
    }


    public void movimentacao() {
        HashMap<String , Object> map = new HashMap<>();
        List<Object> itensVisualizacao = new ArrayList<>();


        List<Item> itens = itemService.listar();

        for(Item item : itens) {

            map.put("id" , item.getId());
            map.put("codigo" , item.getCodigo());
            map.put("nome" , item.getDescricao());
            map.put("quantidade" , 0);
            map.put("cupomFiscal" , " ");
            map.put("notaFiscal" , " ");
            map.put("valor" , 0.0);

            itensVisualizacao.add(map);
            map = new HashMap<>();
        }


        System.out.println(itensVisualizacao);

        this.itensHash = itensVisualizacao;


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

    public List<Object> getItensHash() {
        return itensHash;
    }

    public void setItensHash(List<Object> itensHash) {
        this.itensHash = itensHash;
    }

    public String getCodFinal() {
        return codFinal;
    }

    public void setCodFinal(String codFinal) {
        this.codFinal = codFinal;
    }

    public List<Estoque> getItens() {
        return itens;
    }

    public void setItens(List<Estoque> itens) {
        this.itens = itens;
    }

    //--------------------------------------------------------------//

}
