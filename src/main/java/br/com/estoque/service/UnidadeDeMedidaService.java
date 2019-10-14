package br.com.estoque.service;


import br.com.estoque.dao.UnidadeDeMedidaDao;
import br.com.estoque.model.UnidadeDeMedida;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class UnidadeDeMedidaService implements Serializable {

    @Inject
    private UnidadeDeMedidaDao unidadeDeMedidaDao;

    public void salvar(UnidadeDeMedida unidadeDeMedida) {

        if(unidadeDeMedida.getId() >0) {
            unidadeDeMedidaDao.update(unidadeDeMedida);
        } else
            unidadeDeMedidaDao.save(unidadeDeMedida);
    }

    public List<UnidadeDeMedida> listarTodos() {
        return unidadeDeMedidaDao.listar(UnidadeDeMedida.class);
    }

    public void excluir(UnidadeDeMedida unidadeDeMedida) {
        unidadeDeMedidaDao.delete(unidadeDeMedida,  unidadeDeMedida.getId());
    }

    public UnidadeDeMedida busca(Long id) {
        return unidadeDeMedidaDao.findById(id);
    }



}
