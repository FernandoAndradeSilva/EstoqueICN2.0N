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

        DefaultMenuItem defaultMenu = new DefaultMenuItem();
        defaultMenu.setValue("ENTRADAS");
        defaultMenu.setUrl("movimentacoes/cadastro.xhtml?tipo=1");
        menuMovimentacoes.addElement(defaultMenu);

        defaultMenu = new DefaultMenuItem();
        defaultMenu.setValue("SAÍDAS");
        defaultMenu.setUrl("movimentacoes/cadastro.xhtml?tipo=2");
        menuMovimentacoes.addElement(defaultMenu);

        defaultMenu = new DefaultMenuItem();
        defaultMenu.setValue("ESTORNOS");
        menuMovimentacoes.addElement(defaultMenu);

        defaultMenu = new DefaultMenuItem();
        defaultMenu.setValue("TRANSFERÊNCIAS");
        menuMovimentacoes.addElement(defaultMenu);



    }

    public void construirMenuItens() {
        menuItens = new DefaultMenuModel();
        List<Grupo> grupos = grupoService.listar();


        DefaultMenuItem item = new DefaultMenuItem("TODOS ITENS");
        item.setUrl("item/lista.xhtml");
        menuItens.addElement(item);

        for(Grupo grupo : grupos) {
            DefaultSubMenu submenu = new DefaultSubMenu(grupo.getNome());

            for(Classe classe : grupo.getClasses()) {
                item = new DefaultMenuItem(classe.getNome());
                submenu.addElement(item);
            }
            menuItens.addElement(submenu);
        }

        item = new DefaultMenuItem("CADASTRAR ITEM");
        item.setIcon("fa fa-plus");
        item.setUrl("item/cadastro.xhtml");
        menuItens.addElement(item);
    }


    public void construirMenuUnidades() {

        menuUnidades = new DefaultMenuModel();
        List<Unidade> unidades = unidadeService.listar();

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
