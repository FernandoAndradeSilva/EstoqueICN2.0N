package br.com.estoque.beans;



import br.com.estoque.model.Tipo;
import br.com.estoque.service.TipoService;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@Named
@ViewScoped
public class TipoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TipoService tipoService;

    private Tipo novoTipo = new Tipo();



    private boolean editandoTipo = false;
    private boolean cadastrandoTipo = false;

    public void salvarNovoTipo() {
        tipoService.salvar(novoTipo);

    }


    public void cadastroTipo() {
        this.cadastrandoTipo = true;
    }

    public Tipo getNovoTipo() {
        return novoTipo;
    }

    public void setNovoTipo(Tipo novoTipo) {
        this.novoTipo = novoTipo;
    }

    public boolean isEditandoTipo() {
        return editandoTipo;
    }

    public void setEditandoTipo(boolean editandoTipo) {
        this.editandoTipo = editandoTipo;
    }

    public boolean isCadastrandoTipo() {
        return cadastrandoTipo;
    }

    public void setCadastrandoTipo(boolean cadastrandoTipo) {
        this.cadastrandoTipo = cadastrandoTipo;
    }
}
