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

    private String campoBuscaTipo;
    private String campoBuscaCategoria;


    private List<Categoria> categorias = new ArrayList<>();

    private List<Tipo> tiposFiltrados;

    @Transacional
    public void excluirCategoria() {
        categoriaService.excluir(novaCategoria);
    }

    public void selecionaCategoria(Categoria categoria) {
        this.categoria = categoria;
        if(this.campoBuscaTipo != "") {
            this.campoBuscaTipo = "";
        }
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

    public void buscaManual(int tipoCampoPesquisa) {

        Categoria cat = categoriaService.busca(this.getCategoria().getId());
        this.getCategoria().setTipos(cat.getTipos());

        List<Tipo> listResult = new ArrayList<>();

        for (Tipo tipo : this.getCategoria().getTipos()) {

            if(tipoCampoPesquisa == 1) {
                if(tipo.getSigla().equalsIgnoreCase(this.campoBuscaTipo)) {
                    listResult.add(tipo);
                }
            } else if(tipoCampoPesquisa == 2) {
                if(tipo.getNome().equalsIgnoreCase(this.campoBuscaTipo)) {
                    listResult.add(tipo);
                }
            }
        }
        if(listResult.size() >0) {
            this.getCategoria().setTipos(listResult);
        }
    }

    public void recarregarBusca() {
            tipo.setCategoria(this.categoria);
            Categoria cat = categoriaService.busca(this.getCategoria().getId());
            this.getCategoria().setTipos(cat.getTipos());
            this.tipo = new Tipo();
    }


    public List<Categoria> getCategorias() {
        categorias = categoriaService.listarTodas();
        return categorias;
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

    public String getCampoBuscaTipo() {
        return campoBuscaTipo;
    }

    public void setCampoBuscaTipo(String campoBuscaTipo) {
        this.campoBuscaTipo = campoBuscaTipo;
    }

    public void setNovaCategoria(Categoria novaCategoria) {
        this.novaCategoria = novaCategoria;
    }

    public List<Tipo> getTiposFiltrados() {
        return tiposFiltrados;
    }

    public void setTiposFiltrados(List<Tipo> tiposFiltrados) {
        this.tiposFiltrados = tiposFiltrados;
    }

    public String getCampoBuscaCategoria() {
        return campoBuscaCategoria;
    }

    public void setCampoBuscaCategoria(String campoBuscaCategoria) {
        this.campoBuscaCategoria = campoBuscaCategoria;
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
