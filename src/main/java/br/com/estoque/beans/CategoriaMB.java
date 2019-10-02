package br.com.estoque.beans;


import br.com.estoque.model.Categoria;
import br.com.estoque.model.Tipo;
import br.com.estoque.service.CategoriaService;
import br.com.estoque.service.TipoService;
import br.com.estoque.util.Transacional;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CategoriaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CategoriaService categoriaService;
    @Inject
    private TipoService tipoService;

    public void imprimeCatgTeste() {
        System.out.println(categoriaSelecionada);
    }

    private boolean editandoTipo = false;
    private boolean cadastrandoTipo = false;

    private boolean editandoCategoria = false;
    private boolean cadastrandoCategoria = false;

    private Categoria categoria = new Categoria();

    private Categoria categoriaSelecionada = new Categoria();
    private Tipo tipoSelecionado = new Tipo();

    private Tipo novoTipo = new Tipo();
    private Categoria novaCategoria = new Categoria();


    private String campoPesquisaCategoria = null;
    private String campoPesquisaTipo = null;


    private List<Categoria> categorias = new ArrayList<>();

    public void cadastroInicialTipo() {
        this.novoTipo.setCategoria(this.categoriaSelecionada);
        this.cadastrandoTipo = true;
    }

    public void cadastroInicialCategoria() {
        this.novaCategoria = new Categoria();
        this.cadastrandoCategoria = true;
    }

    public void edicaoInicialCategoria() {
        this.editandoCategoria = true;
    }

    public void edicaoInicialTipo() {
        this.editandoTipo = true;
    }

    public void cancelaCadastroCategoria() {
        this.novaCategoria = new Categoria();
        this.cadastrandoCategoria = false;
        this.editandoCategoria = false;
    }


    public void cancelaCadastroTipo() {
        this.novoTipo = new Tipo();
        this.tipoSelecionado = new Tipo();
        this.cadastrandoTipo = false;
        this.editandoTipo = false;
    }



    public void pesquisarCategorias() {

        this.carregaCategorias();
        List<Categoria> categoriaPesquisada = new ArrayList<>();

        for (Categoria categoria : this.categorias) {
            if(categoria.getNome().equalsIgnoreCase(campoPesquisaCategoria)) {
                categoriaPesquisada.add(categoria);
                break;
            }
        }
        if(categoriaPesquisada.size() == 1) {
            this.categorias = categoriaPesquisada;
        }
    }


    public void pesquisarTipos() {

        this.atualizaPesquisaTipo();
        List<Tipo> tipoPesquisado = new ArrayList<>();

        for (Tipo tipo : this.categoriaSelecionada.getTipos()) {
            if(tipo.getNome().equalsIgnoreCase(campoPesquisaTipo)) {
                tipoPesquisado.add(tipo);
                break;
            }
        }
        if(tipoPesquisado.size() == 1) {
            this.categoriaSelecionada.setTipos(tipoPesquisado);
        }
    }

    public void atualizaPesquisaTipo() {
        this.categoriaSelecionada = categoriaService.busca(categoriaSelecionada);
    }


    @PostConstruct
    public void carregaCategorias() {
       categorias = categoriaService.listarTodas();
    }

    public void recarregarPesquisa() {
        this.carregaCategorias();
        if(campoPesquisaCategoria != null) {
            campoPesquisaCategoria = null;
        }
    }

    @Transacional
    public void salvarNovaCategoria() {
        this.categoriaService.salvar(this.novaCategoria);
        this.carregaCategorias();
        this.novaCategoria = new Categoria();

    }

    @Transacional
    public void excluirCategoriaSelecionada() {
        this.categoriaService.excluir(categoriaSelecionada);
        this.carregaCategorias();
        this.categoriaSelecionada = new Categoria();

    }

    @Transacional
    public void excluirTipoSelecionado() {
        this.tipoService.excluir(tipoSelecionado);
        this.carregaCategorias();
        System.out.println(this.categorias.get(0).getTipos());
    }

    public void testar() {
        this.carregaCategorias();
        System.out.println(this.categorias.get(0));
//        this.atualizaPesquisaTipo();
//        this.tipoSelecionado = new Tipo();
    }


    @Transacional
    public void salvarNovoTipo() {

        tipoService.salvar(novoTipo);
        categoriaSelecionada = categoriaService.busca(novoTipo.getCategoria());
        this.novoTipo = new Tipo();
        this.cadastrandoTipo = false;
        this.carregaCategorias();
    }



    public Categoria getCategoriaSelecionada() {
        return categoriaSelecionada;
    }

    public void setCategoriaSelecionada(Categoria categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

    public Categoria getNovaCategoria() {
        return novaCategoria;
    }

    public void setNovaCategoria(Categoria novaCategoria) {
        this.novaCategoria = novaCategoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public String getCampoPesquisaCategoria() {
        return campoPesquisaCategoria;
    }

    public void setCampoPesquisaCategoria(String campoPesquisaCategoria) {
        this.campoPesquisaCategoria = campoPesquisaCategoria;
    }

    public boolean isEditandoCategoria() {
        return editandoCategoria;
    }

    public void setEditandoCategoria(boolean editandoCategoria) {
        this.editandoCategoria = editandoCategoria;
    }

    public boolean isCadastrandoCategoria() {
        return cadastrandoCategoria;
    }

    public void setCadastrandoCategoria(boolean cadastrandoCategoria) {
        this.cadastrandoCategoria = cadastrandoCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public Tipo getNovoTipo() {
        return novoTipo;
    }

    public void setNovoTipo(Tipo novoTipo) {
        this.novoTipo = novoTipo;
    }

    public Tipo getTipoSelecionado() {
        return tipoSelecionado;
    }

    public void setTipoSelecionado(Tipo tipoSelecionado) {
        this.tipoSelecionado = tipoSelecionado;
    }

    public String getCampoPesquisaTipo() {
        return campoPesquisaTipo;
    }

    public void setCampoPesquisaTipo(String campoPesquisaTipo) {
        this.campoPesquisaTipo = campoPesquisaTipo;
    }
}
