package br.com.estoque.beans;


import br.com.estoque.model.Item;
import br.com.estoque.model.Movimentacao;
import br.com.estoque.service.EstoqueService;
import br.com.estoque.service.ItemService;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Named
@ViewScoped
public class MovimentacaoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    //--------------------------INJECTS----------------------------//
    @Inject
    private ItemService itemService;
    @Inject
    private EstoqueService estoqueService;

    //--------------------------------------------------------------//



    //--------------------------MODELS-----------------------------//

    private Movimentacao movimentacao = new Movimentacao();
    private List <Object> carrinhoMovimentacoes = new ArrayList<>();
    private List<Object> itensHash = new ArrayList<>();


    //--------------------------------------------------------------//



    //----------------------OUTROS ATRIBUTOS------------------------//


    //--------------------------------------------------------------//



    //--------------------------MÉTODOS---------------------------//

    public void adicionarMovimentacao(Object movimentacao) {

        this.carrinhoMovimentacoes.add(movimentacao);
        this.constroiHash();
    }




    public void imprimeCarrinho() {
        System.out.println(this.carrinhoMovimentacoes);
    }



    public void constroiHash() {
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
        this.itensHash = itensVisualizacao;


    }

    //--------------------------------------------------------------//



    //----------------------GETTERS E SETTERS-----------------------//


    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public List<Object> getCarrinhoMovimentacoes() {
        return carrinhoMovimentacoes;
    }

    public void setCarrinhoMovimentacoes(List<Object> carrinhoMovimentacoes) {
        this.carrinhoMovimentacoes = carrinhoMovimentacoes;
    }

    public List<Object> getItensHash() {
        return itensHash;
    }

    public void setItensHash(List<Object> itensHash) {
        this.itensHash = itensHash;
    }



    public String imprimeEntrada() {
        System.out.println("Entradas");
        return "Entradas";
    }

    public String imprimeSaida() {
        System.out.println("Saidas");
        return "Saidas";
    }

    public String imprimeEstorno() {
        System.out.println("Estornos");
        return "Estornos";
    }
}
