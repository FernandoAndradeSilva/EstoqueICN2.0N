package br.com.estoque.util;

import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;
import br.com.estoque.service.GrupoService;
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

    private MenuModel model;

    @PostConstruct
    public void construir() {

        model = new DefaultMenuModel();

        List<Grupo> grupos = grupoService.listarTodos();

        for(Grupo grupo : grupos) {
            DefaultSubMenu submenu = new DefaultSubMenu(grupo.getNome());

            for(Classe classe : grupo.getClasses()) {
                DefaultMenuItem item = new DefaultMenuItem(classe.getNome());
                submenu.addElement(item);
            }

            model.addElement(submenu);
        }


    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
}
