package br.com.estoque.service;


import br.com.estoque.dao.UnidadeDeMedidaDao;
import br.com.estoque.model.UnidadeDeMedida;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class UnidadeDeMedidaService implements Serializable , AbstractService <UnidadeDeMedida , Long>{

    @Inject
    private UnidadeDeMedidaDao unidadeDeMedidaDao;


    @Override
    public void salvar(UnidadeDeMedida entidade) {
        if(entidade.getId() >0) {
            unidadeDeMedidaDao.update(entidade);
        } else
            unidadeDeMedidaDao.save(entidade);
    }

    @Override
    public UnidadeDeMedida salvaRetorna(UnidadeDeMedida entidade) {
        return unidadeDeMedidaDao.saveReturn(entidade);
    }

    @Override
    public void editar(UnidadeDeMedida entidade) {

    }

    @Override
    public void excluir(UnidadeDeMedida entidade) {

    }

    @Override
    public UnidadeDeMedida buscar(UnidadeDeMedida entidade) {
        return null;
    }

    @Override
    public UnidadeDeMedida buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<UnidadeDeMedida> listar() {
        return unidadeDeMedidaDao.listar(UnidadeDeMedida.class);
    }
}
