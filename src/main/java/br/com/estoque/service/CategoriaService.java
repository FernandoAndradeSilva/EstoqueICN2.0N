package br.com.estoque.service;

import br.com.estoque.dao.CategoriaDao;
import br.com.estoque.model.Categoria;


import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class CategoriaService implements Serializable {

    @Inject
    private CategoriaDao categoriaDao;

    public void salvar(Categoria categoria) {

        if(categoria.getId() >0) {
            categoriaDao.update(categoria);
        } else
        categoriaDao.save(categoria);
    }

    public List<Categoria> listarTodas() {
        return categoriaDao.listar(Categoria.class);
    }

    public void excluir(Categoria categoria) {
        categoriaDao.delete(categoria ,  categoria.getId());
    }
}
