package br.com.estoque.service;

import br.com.estoque.dao.SetorDao;
import br.com.estoque.model.Setor;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class SetorService implements Serializable, AbstractService<Setor , Long> {

    @Inject
    private SetorDao setorDao;


    @Override
    public void salvar(Setor entidade) {
        if(entidade.getId() >0) {
            setorDao.update(entidade);
        } else
            setorDao.save(entidade);
    }

    @Override
    public Setor salvaRetorna(Setor entidade) {
        return null;
    }

    @Override
    public void editar(Setor entidade) {

    }

    @Override
    public void excluir(Setor entidade) {
        setorDao.delete(entidade,  entidade.getId());
    }

    @Override
    public Setor buscar(Setor entidade) {
        return setorDao.findById(entidade.getId());
    }

    @Override
    public Setor buscarPorId(Long id) {
        return setorDao.findById(id);
    }

    @Override
    public List<Setor> listar() {
        return setorDao.listar(Setor.class);
    }
}
