package br.com.estoque.beans;

import br.com.estoque.model.UnidadeDeMedida;
import br.com.estoque.service.UnidadeDeMedidaService;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;
import org.primefaces.component.inputtext.InputText;

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


    private InputText test;

    @Inject
    private UnidadeDeMedidaService unidadeDeMedidaService;

    private UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();

    private List<UnidadeDeMedida> unidadesDeMedida = new ArrayList<>();
    private Object UnidadeDeMedida;


    public void carregaUnidadesDeMedida() {
        this.unidadesDeMedida = unidadeDeMedidaService.listarTodos();
    }

    @Transacional
    public void salvar() {

        unidadeDeMedidaService.salvar(unidadeDeMedida);
        this.carregaUnidadesDeMedida();
        this.unidadeDeMedida = new UnidadeDeMedida();
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
    }

    @Transacional
    public void excluir() {


        unidadeDeMedidaService.excluir(unidadeDeMedida);
        this.carregaUnidadesDeMedida();
        this.unidadeDeMedida = new UnidadeDeMedida();
        MessageUtil.addMessageTicket("Excluido com sucesso" , MessageUtil.FATAL , MessageUtil.NOREDIRECT);
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

    public InputText getTest() {
        return test;
    }

    public void setTest(InputText test) {
        this.test = test;
    }
}
