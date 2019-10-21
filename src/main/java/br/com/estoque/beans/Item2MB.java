package br.com.estoque.beans;

import br.com.estoque.model.*;
import br.com.estoque.service.*;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class Item2MB implements Serializable {

    private static final long serialVersionUID = 1L;


    //----------INJECTS---------------//

    @Inject
    private ItemService itemService;
    @Inject
    private ClasseService classeService;
    @Inject
    private GrupoService grupoService;
    @Inject
    private UnidadeDeMedidaService unidadeDeMedidaService;

    //---------FIM INJECTS-----------//



    //----------MODELS---------------//

    private Item item = new Item();
    private Grupo grupo = new Grupo();
    private Classe classe = new Classe();

    //---------FIM MODELS-----------//



    //-----------OUTROS-------------//

    private boolean quantidadeMinima = false;
    private int codFinal;

    //-----------FIM OUTROS---------//



    //-----------MÉTODOS TRANSCIONAIS -----------//

    @Transacional
    public void salvaItem() {
        this.item.configuraCodigo(this.codFinal);
        this.itemService.salvar(this.item);
        this.item = new Item();
        this.quantidadeMinima = false;
        this.codFinal = 0;

        MessageUtil.addMessageTicket("Item cadastrado com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);

    }

    //----------FIM MÉTODOS TRANSCIONAIS---------//



    //--------------OUTROS MÉTODOS---------------//

    public void testeImpressao() {

        //        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("PF('dlgCadastroUnidadeDeMedida').hide();");
    }



    public void recarregaClasses() {
        item.getClasse().getGrupo().setClasses(classeService.listarPorGrupo(item.getClasse().getGrupo()));
        this.configuraPreCodigo();
    }

    public void recarregaClassesCadastroItem() {
        if(this.item.getClasse().getGrupo().getNome() != null) {
            this.item.getClasse().getGrupo().setClasses(classeService.listarPorGrupo(item.getClasse().getGrupo()));
        }


    }

    public void configuraPreCodigo() {
        this.item.setCodigo(item.getClasse().getGrupo().getSigla() + item.getClasse().getSigla());
    }



    //------------FIM OUTROS MÉTODOS-------------//



    //------------GETERS E SETTERS---------------//
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



    public int getCodFinal() {
        return codFinal;
    }

    public void setCodFinal(int codFinal) {
        this.codFinal = codFinal;
    }
}
