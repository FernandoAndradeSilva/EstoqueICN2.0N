package br.com.estoque.service;

import br.com.estoque.dao.SetorDao;
import br.com.estoque.model.Setor;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class SetorService implements Serializable {

    @Inject
    private SetorDao setorDao;

    public void salvar(Setor setor) {

        if(setor.getId() >0) {
            setorDao.update(setor);
        } else
        setorDao.save(setor);
    }

    public List<Setor> listarTodos() {
        return setorDao.listar(Setor.class);
    }

    public void excluir(Setor setor) {
        setorDao.delete(setor,  setor.getId());
    }

    public Setor busca(Long id) {
        return setorDao.findById(id);
    }



}
