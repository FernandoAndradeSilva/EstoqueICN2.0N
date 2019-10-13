package br.com.estoque.beans;


import br.com.estoque.enums.TipoUsuario;
import br.com.estoque.model.Estoque;
import br.com.estoque.model.Grupo;
import br.com.estoque.model.Item;
import br.com.estoque.model.Usuario;
import br.com.estoque.service.EstoqueService;
import br.com.estoque.service.ItemService;
import br.com.estoque.util.Transacional;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.EnumSet;


@Named
@ViewScoped
public class ItemMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ItemService itemService;


    @Inject
    private EstoqueService estoqueService;

    private Grupo grupoCadastro = new Grupo();

    private boolean quantidadeMinima = false;

    private Item item = new Item();

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

    public Grupo getGrupoCadastro() {
        return grupoCadastro;
    }

    public void setGrupoCadastro(Grupo grupoCadastro) {
        this.grupoCadastro = grupoCadastro;
    }

    public Usuario retornaUsuarioDaSessao() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }
}
