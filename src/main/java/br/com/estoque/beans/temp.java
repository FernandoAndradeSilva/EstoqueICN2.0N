package br.com.estoque.beans;


import br.com.estoque.model.*;
import br.com.estoque.service.EstoqueService;

import br.com.estoque.service.ItemService;
import br.com.estoque.service.UnidadeDeMedidaService;
import br.com.estoque.util.Transacional;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;



public class temp implements Serializable {

    private static final long serialVersionUID = 1L;


    @Inject
    private ItemService itemService;
    @Inject
    private EstoqueService estoqueService;
    @Inject
    private UnidadeDeMedidaService unidadeDeMedidaService;




    private boolean quantidadeMinima = false;
    private Item item = new Item();





    public void limparClasse() {
        this.item.setClasse(new Classe());
    }
    public void verificaGrupoPreenchido() {
        if(item.getClasse() == null ) {
            System.out.println("Classse Nula");
            this.item.setClasse(new Classe());
        } else {
            System.out.println(this.item.getClasse().getId());
        }
    }


    @Transacional
    public void salvar() {

        itemService.salvar(item);
        System.out.println(retornaUsuarioDaSessao().getSetor().getUnidade());
        Estoque estoque = new Estoque(this.item , this.retornaUsuarioDaSessao().getSetor().getUnidade());
        System.out.println(estoque.getEntradas());
        estoque.atualizaSaldo(500 , Estoque.ENTRADA);
        estoqueService.salvar(estoque);

        //estoqueService.salvar(new Estoque(this.item , this.retornaUsuarioDaSessao().getSetor().getUnidade()));
    }


    public void verificaUnidadeExistente() {
        UnidadeDeMedida und = unidadeDeMedidaService.busca(item.getUnidadeDeMedida().getId());
        if(und == null) {
            this.item.setUnidadeDeMedida(new UnidadeDeMedida());
        }
    }



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



    public Usuario retornaUsuarioDaSessao() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }



}
