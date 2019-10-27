package br.com.estoque.beans;


import br.com.estoque.model.*;
import br.com.estoque.security.LogonMB;
import br.com.estoque.service.ClasseService;
import br.com.estoque.service.EstoqueService;
import br.com.estoque.service.ItemService;
import br.com.estoque.service.MovimentacaoService;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;


@Named
@ViewScoped
public class MovimentacaoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    //--------------------------INJECTS----------------------------//
    @Inject
    private ItemService itemService;
    @Inject
    private EstoqueService estoqueService;
    @Inject
    private MovimentacaoService movimentacaoService;
    @Inject
    private ClasseService classeService;
    //--------------------------------------------------------------//



    //--------------------------MODELS-----------------------------//

    private Grupo grupoFiltro = new Grupo();
    private Classe classeFiltro = new Classe();

    private List<Movimentacao> movimentacoes = new ArrayList<>();
    private List<Movimentacao> carrinhoMovimentacoes = new ArrayList<>();

    private Movimentacao movimentacao = new Movimentacao();

    private int tipoMovimentacao = 0;


    //--------------------------------------------------------------//



    //----------------------OUTROS ATRIBUTOS------------------------//


    //--------------------------------------------------------------//



    //--------------------------MÉTODOS---------------------------//

    public void adicionarMovimentacao(Movimentacao movimentacao) {
        this.movimentacao.setTipo(this.tipoMovimentacao);
        this.carrinhoMovimentacoes.add(this.movimentacao);
        MessageUtil.addMessageTicket(movimentacao.getItem().getDescricao() +" Adicionado" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.movimentacao = new Movimentacao();
        this.constroiMovimentacoes();


    }

    public void imprimeCarrinho() {
        System.out.println(this.carrinhoMovimentacoes);
    }

    public void constroiMovimentacoes() {

        List<Item> itens = itemService.listar();
        this.movimentacoes = new ArrayList<>();

        for(Item item : itens) {
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setItem(item);

            if(grupoFiltro != null && grupoFiltro.getId() != 0) { // FILTRO DO GRUPO SELECIONADO
                if(movimentacao.getItem().getClasse().getGrupo().getId() == grupoFiltro.getId())

                    if(classeFiltro != null && classeFiltro.getId() != 0 ) { // FILTRO CLASSE SELECIONADO
                        if(movimentacao.getItem().getClasse().getId() == classeFiltro.getId()) {
                            movimentacoes.add(movimentacao);
                        }

                    } else
                movimentacoes.add(movimentacao);
            } else {
                movimentacoes.add(movimentacao);
            }


        }
    }

    @Transacional
    public void salvarMovimentacoes() {

        for(Movimentacao movimentacao : this.carrinhoMovimentacoes) {
            movimentacao.setItem(itemService.buscarPorId(movimentacao.getItem().getId()));
            movimentacao.setUsuario(LogonMB.usuarioDaSessao());

            Estoque estoque = estoqueService.buscarPorId(LogonMB.usuarioDaSessao().getSetor().getUnidade() , movimentacao.getItem());
            estoque.atualizaSaldo(movimentacao.getQuantidade() , movimentacao.getTipo());
            estoqueService.salvar(estoque);
            movimentacaoService.salvar(movimentacao);

        }
        MessageUtil.addMessageTicket("Movimentações realizadas com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.limpaCarrinho();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgCarrinhoMovimetacoes').hide();");


    }

    public List<Classe> retornaClasses(Grupo grupo) {
        if(grupo == null) {
            return new ArrayList<>();
        } else
        return classeService.listarPorGrupo(grupo);
    }

    public boolean carrinhoVazio() {
        if(this.carrinhoMovimentacoes.size() == 0) {
            return true;
        } else
            return false;
    }

    public void removeMovimentacao(Movimentacao movimentacao) {
        this.carrinhoMovimentacoes.remove(movimentacao);

    }

    public String retornaNomeMovimentacao() {
        if(tipoMovimentacao == 1) {
            return "ENTRADA";
        } else if(tipoMovimentacao == 2) {
            return "SAÍDA";
        }
        return " ";
    }

    public void limpaCarrinho() {
        this.carrinhoMovimentacoes = new ArrayList<>();
    }







    //--------------------------------------------------------------//



    //----------------------GETTERS E SETTERS-----------------------//




    public Classe getClasseFiltro() {
        return classeFiltro;
    }

    public void setClasseFiltro(Classe classeFiltro) {
        this.classeFiltro = classeFiltro;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public List<Movimentacao> getCarrinhoMovimentacoes() {
        return carrinhoMovimentacoes;
    }

    public void setCarrinhoMovimentacoes(List<Movimentacao> carrinhoMovimentacoes) {
        this.carrinhoMovimentacoes = carrinhoMovimentacoes;
    }

    public int getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(int tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public Grupo getGrupoFiltro() {
        return grupoFiltro;
    }

    public void setGrupoFiltro(Grupo grupoFiltro) {
        this.grupoFiltro = grupoFiltro;
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
