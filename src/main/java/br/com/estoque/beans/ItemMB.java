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


    //----------INJECTS---------------//
    @Inject
    private ItemService itemService;
    @Inject
    //---------FIM INJECTS-----------//


    //----------MODELS---------------//
    private Item item = new Item();
    private Grupo grupo = new Grupo();
    private Classe classe = new Classe();
    //---------FIM MODELS-----------//


    //-----------OUTROS-------------//
    private boolean quantidadeMinima = false;
    //-----------FIM OUTROS---------//


    //-----------MÉTODOS TRANSCIONAIS -----------//
    @Transacional
    public void salvaItem() {
        this.itemService.salvar(this.item);
        this.item = new Item();
    }
    //----------FIMMÉTODOS TRANSCIONAIS----------//






    public void testeImpressao() {

        //        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("PF('dlgCadastroUnidadeDeMedida').hide();");
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


}
