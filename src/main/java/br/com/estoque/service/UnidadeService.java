package br.com.estoque.service;

import br.com.estoque.dao.UnidadeDao;
import br.com.estoque.model.Unidade;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class UnidadeService implements Serializable , AbstractService <Unidade , Long> {

    @Inject
    private UnidadeDao unidadeDao;


    @Override
    public void salvar(Unidade entidade) {
        if(entidade.getId() >0) {
            unidadeDao.update(entidade);
        } else
            unidadeDao.save(entidade);
    }

    @Override
    public Unidade salvaRetorna(Unidade entidade) {
        return null;
    }

    @Override
    public void editar(Unidade entidade) {

    }

    @Override
    public void excluir(Unidade entidade) {
        unidadeDao.delete(entidade,  entidade.getId());
    }

    @Override
    public Unidade buscar(Unidade entidade) {
        return unidadeDao.findById(entidade.getId());
    }

    @Override
    public Unidade buscarPorId(Long id) {
        return unidadeDao.findById(id);
    }

    @Override
    public List<Unidade> listar() {
        return unidadeDao.listar(Unidade.class);
    }
}
