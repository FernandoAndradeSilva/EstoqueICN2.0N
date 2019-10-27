package br.com.estoque.service;

import br.com.estoque.dao.EstoqueDao;
import br.com.estoque.model.Estoque;
import br.com.estoque.model.EstoquePK;
import br.com.estoque.model.Item;
import br.com.estoque.model.Unidade;


import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


public class EstoqueService implements Serializable, AbstractService <Estoque, EstoquePK>{

    @Inject
    private EstoqueDao estoqueDao;


    @Override
    public void salvar(Estoque entidade) {

        estoqueDao.save(entidade);
    }

    @Override
    public Estoque salvaRetorna(Estoque entidade) {
        return null;
    }

    @Override
    public void editar(Estoque entidade) {
    }

    @Override
    public void excluir(Estoque entidade) {
    }

    @Override
    public Estoque buscar(Estoque entidade) {
        return null;
    }

    @Override
    public Estoque buscarPorId(EstoquePK id) {
        return null;
    }

    @Override
    public List<Estoque> listar() {
        return null;
    }

    public void atualizar(Estoque entidade) {
        estoqueDao.update(entidade);
    }

    public Estoque buscarPorId(Unidade unidade , Item item) {
        EstoquePK pk = new EstoquePK(unidade , item);
        return estoqueDao.findById(pk);
    }


}
