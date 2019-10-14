package br.com.estoque.util;

import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;
import br.com.estoque.model.Unidade;
import br.com.estoque.service.GrupoService;
import br.com.estoque.service.UnidadeService;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class MenuConstrutor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private GrupoService grupoService;

    @Inject
    private UnidadeService unidadeService;

    private MenuModel menuItens;

    private MenuModel menuMovimentacoes;

    private MenuModel menuUnidades;


    @PostConstruct
    public void construir() {

       this.construirMenuItens();
       this.construirMenuMovimentacoes();
       this.construirMenuUnidades();
    }


    public void construirMenuMovimentacoes() {

        menuMovimentacoes = new DefaultMenuModel();

        menuMovimentacoes.addElement(new DefaultMenuItem("ENTRADAS"));
        menuMovimentacoes.addElement(new DefaultMenuItem("SAÍDAS"));
        menuMovimentacoes.addElement(new DefaultMenuItem("ESTORNOS"));
        menuMovimentacoes.addElement(new DefaultMenuItem("TRANSFERÊNCIAS"));


    }

    public void construirMenuItens() {
        menuItens = new DefaultMenuModel();
        List<Grupo> grupos = grupoService.listarTodos();

        for(Grupo grupo : grupos) {
            DefaultSubMenu submenu = new DefaultSubMenu(grupo.getNome());

            for(Classe classe : grupo.getClasses()) {
                DefaultMenuItem item = new DefaultMenuItem(classe.getNome());
                submenu.addElement(item);
            }
            menuItens.addElement(submenu);
        }

        DefaultMenuItem item = new DefaultMenuItem("CADASTRAR ITEM");
        item.setIcon("fa fa-plus");
        item.setUrl("item/cadastro.xhtml");

        menuItens.addElement(item);
    }


    public void construirMenuUnidades() {

        menuUnidades = new DefaultMenuModel();
        List<Unidade> unidades = unidadeService.listarTodos();

        for(Unidade unidade : unidades) {
            DefaultMenuItem item = new DefaultMenuItem("UNIDADE "+unidade.getNome());
            menuUnidades.addElement(item);
        }

        menuUnidades.addElement(new DefaultMenuItem("TODAS UNIDADES"));

    }

    public MenuModel getMenuUnidades() {
        return menuUnidades;
    }

    public void setMenuUnidades(MenuModel menuUnidades) {
        this.menuUnidades = menuUnidades;
    }

    public MenuModel getMenuMovimentacoes() {
        return menuMovimentacoes;
    }

    public void setMenuMovimentacoes(MenuModel menuMovimentacoes) {
        this.menuMovimentacoes = menuMovimentacoes;
    }

    public MenuModel getMenuItens() {
        return menuItens;
    }

    public void setMenuItens(MenuModel menuItens) {
        this.menuItens = menuItens;
    }
}
