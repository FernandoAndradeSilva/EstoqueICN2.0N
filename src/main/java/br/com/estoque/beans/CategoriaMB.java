package br.com.estoque.beans;


import br.com.estoque.model.Categoria;
import br.com.estoque.service.CategoriaService;
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

    private Categoria categoria;
    private Categoria categoriaSelecionada = new Categoria();
    private Categoria novaCategoria = new Categoria();
    private String campoPesquisa = null;

    private boolean novoCadastro = false;

    private List<Categoria> categorias = new ArrayList<>();

    public void cadastroInicialNovaCategoria() {
        this.novoCadastro = true;
    }

    public void cancelaCadastroNovaCategoria() {
        this.novoCadastro = false;
    }

    public void pesquisar() {

        this.carregaCategorias();
        List<Categoria> categoriaPesquisada = new ArrayList<>();

        for (Categoria categoria : this.categorias) {
            if(categoria.getNome().equalsIgnoreCase(campoPesquisa)) {
                categoriaPesquisada.add(categoria);
                break;
            }
        }
        if(categoriaPesquisada.size() == 1) {
            this.categorias = categoriaPesquisada;
        }
    }


    @PostConstruct
    public void carregaCategorias() {
       categorias = categoriaService.listarTodas();
    }

    public void recarregarPesquisa() {
        this.carregaCategorias();
        if(campoPesquisa != null) {
            campoPesquisa = null;
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

    public String getCampoPesquisa() {
        return campoPesquisa;
    }

    public void setCampoPesquisa(String campoPesquisa) {
        this.campoPesquisa = campoPesquisa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isNovoCadastro() {
        return novoCadastro;
    }

    public void setNovoCadastro(boolean novoCadastro) {
        this.novoCadastro = novoCadastro;
    }
}
