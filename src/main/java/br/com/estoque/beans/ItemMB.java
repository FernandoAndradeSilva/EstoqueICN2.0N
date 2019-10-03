package br.com.estoque.beans;


import br.com.estoque.model.Item;
import br.com.estoque.service.ItemService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


@Named
@ViewScoped
public class ItemMB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ItemService itemService;

    private Item item = new Item();

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
