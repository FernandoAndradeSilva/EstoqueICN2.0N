package br.com.estoque.beans;


import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;
import br.com.estoque.service.ClasseService;
import br.com.estoque.service.GrupoService;
import br.com.estoque.service.UnidadeDeMedidaService;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class GrupoMB implements Serializable {

    private static final long serialVersionUID = 1L;


    //----------INJECTS---------------//

    @Inject
    private GrupoService grupoService;

    //---------FIM INJECTS-----------//



    //----------MODELS---------------//

    private Grupo grupo = new Grupo();
    private List<Grupo> grupos = new ArrayList<>();

    //---------FIM MODELS-----------//



    //-----------OUTROS-------------//

    private boolean quantidadeMinima = false;

    //-----------FIM OUTROS---------//



    //-----------MÉTODOS TRANSCIONAIS -----------//

    @Transacional
    public void adicionar(boolean redireciona) throws IOException {
        Grupo g = grupoService.salvaRetorna(grupo);
        MessageUtil.addMessageTicket("Adicionado com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.grupo = new Grupo();
        this.carregaGrupos();

        if(redireciona == true) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro.xhtml?idGrupo=" +g.getId());
        }



    }

    @Transacional
    public void excluir() {
        grupoService.excluir(grupo);
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.grupo = new Grupo();
        this.carregaGrupos();
    }

    @Transacional
    public void editarRow(RowEditEvent event) {
        grupoService.salvar(((Grupo) event.getObject()));
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.carregaGrupos();
    }

    //----------FIM MÉTODOS TRANSCIONAIS----------//


    //--------------OUTROS MÉTODOS---------------//

    public void carregaGrupos() {
        this.grupos = this.grupoService.listarTodos();
    }


    //------------FIM OUTROS MÉTODOS------------//


    //------------GETERS E SETTERS-------------//
    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }



    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
