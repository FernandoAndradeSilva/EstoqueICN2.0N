package br.com.estoque.beans;


import br.com.estoque.model.Categoria;
import br.com.estoque.model.Tipo;
import br.com.estoque.service.CategoriaService;
import br.com.estoque.service.TipoService;
import br.com.estoque.util.Transacional;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

@Named
@ViewScoped
public class CategoriaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CategoriaService categoriaService;

    @Inject
    private TipoService tipoService;

    private Categoria categoria = new Categoria();

    private Categoria novaCategoria = new Categoria();

    private Tipo tipo = new Tipo();

    private List<Categoria> categorias = new ArrayList<>();

    public void carregaCategorias() {
        categorias = categoriaService.listarTodas();
    }




    @Transacional
    public void excluirCategoria() {
        categoriaService.excluir(novaCategoria);
    }

    public void selecionaCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Transacional
    public void editarCategoria(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        categoriaService.salvar(((Categoria) event.getObject()));

    }

    @Transacional
    public void editarTipo(RowEditEvent event) {
        //FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        tipoService.salvar(((Tipo) event.getObject()));

    }

    @Transacional
    public void salvarCategoria() {

        this.categoriaService.salvar(novaCategoria);
        this.novaCategoria = new Categoria();
    }

    @Transacional
    public void salvarTipo() {
        this.tipoService.salvar(tipo);
        this.tipo = new Tipo();
    }

    @Transacional
    public void excluirTipo(Tipo tipo) {
        tipoService.excluir(tipo);
    }

    public void atualizaExclusao() {
        Categoria cat = categoriaService.busca(this.getCategoria().getId());
        this.getCategoria().setTipos(cat.getTipos());
        this.tipo = new Tipo();
    }

    @Transacional
    public void adicionaNovoTipo() {
        tipo.setCategoria(this.categoria);
        tipoService.salvar(tipo);
        Categoria cat = categoriaService.busca(this.getCategoria().getId());
        this.getCategoria().setTipos(cat.getTipos());
        this.tipo = new Tipo();

    }




    public List<Categoria> getCategorias() {
        categorias = categoriaService.listarTodas();
        return categorias;
    }


    public void selecionaCategoriaNovoTipo() {
        this.tipo.setCategoria(this.categoria);
    }

    public void setCategorias(List<Categoria> categorias) {

        this.categorias = categorias;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }


    public Categoria getNovaCategoria() {
        return novaCategoria;
    }


    public void setNovaCategoria(Categoria novaCategoria) {
        this.novaCategoria = novaCategoria;
    }



//    public void pesquisarCategorias() {
//
//        this.carregaCategorias();
//        List<Categoria> categoriaPesquisada = new ArrayList<>();
//
//        for (Categoria categoria : this.categorias) {
//            if(categoria.getNome().equalsIgnoreCase(campoPesquisaCategoria)) {
//                categoriaPesquisada.add(categoria);
//                break;
//            }
//        }
//        if(categoriaPesquisada.size() == 1) {
//            this.categorias = categoriaPesquisada;
//        }
//    }
//
//
//    public void pesquisarTipos() {
//
//        this.atualizaPesquisaTipo();
//        List<Tipo> tipoPesquisado = new ArrayList<>();
//
//        for (Tipo tipo : this.categoriaSelecionada.getTipos()) {
//            if(tipo.getNome().equalsIgnoreCase(campoPesquisaTipo)) {
//                tipoPesquisado.add(tipo);
//                break;
//            }
//        }
//        if(tipoPesquisado.size() == 1) {
//            this.categoriaSelecionada.setTipos(tipoPesquisado);
//        }
//    }
}
