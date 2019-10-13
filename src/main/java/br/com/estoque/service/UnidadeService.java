package br.com.estoque.service;

import br.com.estoque.dao.UnidadeDao;
import br.com.estoque.model.Unidade;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class UnidadeService implements Serializable {

    @Inject
    private UnidadeDao unidadeDao;

    public void salvar(Unidade unidade) {

        if(unidade.getId() >0) {
            unidadeDao.update(unidade);
        } else
        unidadeDao.save(unidade);
    }

    public List<Unidade> listarTodos() {
        return unidadeDao.listar(Unidade.class);
    }

    public void excluir(Unidade unidade) {
        unidadeDao.delete(unidade,  unidade.getId());
    }

    public Unidade busca(Long id) {
        return unidadeDao.findById(id);
    }



}
