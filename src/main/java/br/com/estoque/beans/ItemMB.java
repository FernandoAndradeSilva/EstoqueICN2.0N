package br.com.estoque.beans;


import br.com.estoque.enums.TipoUsuario;
import br.com.estoque.model.*;
import br.com.estoque.service.EstoqueService;
import br.com.estoque.service.ItemService;
import br.com.estoque.service.UnidadeDeMedidaService;
import br.com.estoque.util.Transacional;
import org.primefaces.component.inputtext.InputText;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.EnumSet;


@Named
@ViewScoped
public class ItemMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private SelectItem si = new SelectItem();


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


    public void setandoSi() {
        si.setLabel("Nova Unidade");

        UnidadeDeMedida uni = new UnidadeDeMedida();
        uni.setNome("TESTANDO");
        si.setValue(uni);
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

    public SelectItem getSi() {
        return si;
    }

    public void setSi(SelectItem si) {
        this.si = si;
    }
}
