package br.com.estoque.service;


import br.com.estoque.dao.ItemDao;
import br.com.estoque.model.Item;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class ItemService implements Serializable, AbstractService<Item, Long>{

    @Inject
    private ItemDao itemDao;


    @Override
    public void salvar(Item entidade) {
        if(entidade.getId() >0) {
            itemDao.update(entidade);
        } else
            itemDao.save(entidade);
    }

    @Override
    public Item salvaRetorna(Item entidade) {
        return itemDao.saveReturn(entidade);
    }

    @Override
    public void editar(Item entidade) {

    }

    @Override
    public void excluir(Item entidade) {
        itemDao.delete(entidade,  entidade.getId());
    }

    @Override
    public Item buscar(Item entidade) {
        return itemDao.findById(entidade.getId());
    }

    @Override
    public Item buscarPorId(Long id) {
        return itemDao.findById(id);
    }

    @Override
    public List<Item> listar() {
        return itemDao.listar(Item.class);
    }
}
