package br.com.estoque.beans;

import br.com.estoque.model.UnidadeDeMedida;
import br.com.estoque.service.UnidadeDeMedidaService;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UnidadeDeMedidaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------INJECTS---------------//
    @Inject
    private UnidadeDeMedidaService unidadeDeMedidaService;
    //---------FIM INJECTS-----------//


    //------------MODELS-------------//
    private UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
    private List<UnidadeDeMedida> unidadesDeMedida = new ArrayList<>();
    //---------FIM MODELS-----------//


    //-----------OUTROS-------------//



    //---------FIM OUTROS-----------//


    //-----------MÉTODOS TRANSCIONAIS -----------//
    @Transacional
    public void adicionar() {
        unidadeDeMedidaService.salvar(unidadeDeMedida);
        MessageUtil.addMessageTicket("Adicionado com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.unidadeDeMedida = new UnidadeDeMedida();
        this.carregaUnidadesDeMedida();

    }

    @Transacional
    public void excluir() {
        unidadeDeMedidaService.excluir(unidadeDeMedida);
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.unidadeDeMedida = new UnidadeDeMedida();
        this.carregaUnidadesDeMedida();
    }

    @Transacional
    public void editarRow(RowEditEvent event) {
        unidadeDeMedidaService.salvar(((UnidadeDeMedida) event.getObject()));
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.carregaUnidadesDeMedida();
    }

    //----------FIM MÉTODOS TRANSCIONAIS----------//



    //---------------------------------------//
    ////////////////// OUTROS /////////////////
    public void carregaUnidadesDeMedida() {
        this.unidadesDeMedida = unidadeDeMedidaService.listarTodos();
    }

    public List<UnidadeDeMedida> getUnidadesDeMedida() {
        return unidadesDeMedida;
    }

    public void setUnidadesDeMedida(List<UnidadeDeMedida> unidadesDeMedida) {
        this.unidadesDeMedida = unidadesDeMedida;
    }

    public UnidadeDeMedida getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }


}
