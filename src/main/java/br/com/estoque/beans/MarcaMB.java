package br.com.estoque.beans;


import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;
import br.com.estoque.model.Marca;
import br.com.estoque.service.ClasseService;
import br.com.estoque.service.GrupoService;
import br.com.estoque.service.MarcaService;
import br.com.estoque.util.MessageUtil;
import br.com.estoque.util.Transacional;
import org.primefaces.event.RowEditEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@ViewScoped
public class MarcaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    //--------------------------INJECTS----------------------------//

    @Inject
    private MarcaService marcaService;

    //--------------------------------------------------------------//



    //--------------------------MODELS-----------------------------//

    private Marca marca = new Marca();
    private List<Marca> marcas = new ArrayList<>();

    //--------------------------------------------------------------//


    //----------------------OUTROS ATRIBUTOS------------------------//




    //--------------------------------------------------------------//



    //--------------------------MÉTODOS---------------------------//


    @Transacional
    public void salva() {
        Marca m = marcaService.salvaRetorna(marca);
        MessageUtil.addMessageTicket("Adicionado com sucesso", MessageUtil.INFO, MessageUtil.NOREDIRECT);
        this.marca = new Marca();
        this.carregaMarcas();
    }

    @Transacional
    public void excluir() {
        marcaService.excluir(marca);
        MessageUtil.addMessageTicket("Removido com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.marca = new Marca();
        this.carregaMarcas();
    }

    @Transacional
    public void editarRow(RowEditEvent event) {
        marcaService.salvar((Marca) event.getObject());
        MessageUtil.addMessageTicket("Salvo com sucesso" , MessageUtil.INFO , MessageUtil.NOREDIRECT);
        this.carregaMarcas();
    }

    public void carregaMarcas() {
        this.marcas = marcaService.listar();
    }


    //--------------------------------------------------------------//



    //---------------------GETTERS E SETTERS------------------------//


    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }
}
