package br.com.estoque.beans;


import br.com.estoque.model.Categoria;
import br.com.estoque.service.CategoriaService;

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

    private Categoria categoriaSelecionada;
    private Categoria novaCategoria;




    private List<Categoria> categorias = new ArrayList<>();



    public void testeImprimir() {
        System.out.println(this.novaCategoria);
    }

    @PostConstruct
    public void carregaCategorias() {
       categorias = categoriaService.listarTodas();
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
}
