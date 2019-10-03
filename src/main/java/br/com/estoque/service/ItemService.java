package br.com.estoque.service;


import br.com.estoque.dao.ItemDao;
import br.com.estoque.model.Item;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class ItemService implements Serializable {

    @Inject
    private ItemDao itemDao;

    public void salvar(Item item) {

        if(item.getId() >0) {
            itemDao.update(item);
        } else
            itemDao.save(item);
    }

    public List<Item> listarTodas() {
        return itemDao.listar(Item.class);
    }

    public void excluir(Item item) {
        itemDao.delete(item ,  item.getId());
    }

    public Item salvaRetorna(Item item) {
        return itemDao.saveReturn(item);
    }
}
