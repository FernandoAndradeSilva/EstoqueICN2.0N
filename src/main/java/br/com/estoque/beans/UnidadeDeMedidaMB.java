package br.com.estoque.beans;

import br.com.estoque.model.UnidadeDeMedida;
import br.com.estoque.service.UnidadeDeMedidaService;

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

    @Inject
    private UnidadeDeMedidaService unidadeDeMedidaService;

    private List<UnidadeDeMedida> unidadesDeMedida = new ArrayList<>();


    public void carregaUnidadesDeMedida() {
        this.unidadesDeMedida = unidadeDeMedidaService.listarTodos();
    }


    public List<UnidadeDeMedida> getUnidadesDeMedida() {
        return unidadesDeMedida;
    }

    public void setUnidadesDeMedida(List<UnidadeDeMedida> unidadesDeMedida) {
        this.unidadesDeMedida = unidadesDeMedida;
    }
}
