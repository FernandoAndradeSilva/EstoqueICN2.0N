package br.com.estoque.service;

import br.com.estoque.dao.MovimentacaoDao;
import br.com.estoque.model.Movimentacao;
import br.com.estoque.model.Movimentacao;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class MovimentacaoService implements Serializable , AbstractService<Movimentacao, Long> {


    @Inject
    private MovimentacaoDao grupoDao;

    @Override
    public void salvar(Movimentacao entidade) {
        if(entidade.getId() >0) {
            grupoDao.update(entidade);
        } else
            grupoDao.save(entidade);
    }

    @Override
    public Movimentacao salvaRetorna(Movimentacao entidade) {
        return grupoDao.saveReturn(entidade);
    }

    @Override
    public void editar(Movimentacao entidade) {

    }

    @Override
    public void excluir(Movimentacao entidade) {
        grupoDao.delete(entidade,  entidade.getId());
    }

    @Override
    public Movimentacao buscar(Movimentacao entidade) {
        return grupoDao.findById(entidade.getId());
    }

    @Override
    public Movimentacao buscarPorId(Long id) {
        return grupoDao.findById(id);
    }

    @Override
    public List<Movimentacao> listar() {
        return grupoDao.listar(Movimentacao.class);
    }


}
