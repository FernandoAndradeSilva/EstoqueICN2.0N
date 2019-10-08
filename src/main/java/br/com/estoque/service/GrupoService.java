package br.com.estoque.service;

import br.com.estoque.dao.GrupoDao;
import br.com.estoque.model.Grupo;


import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class GrupoService implements Serializable {

    @Inject
    private GrupoDao grupoDao;

    public void salvar(Grupo grupo) {

        if(grupo.getId() >0) {
            grupoDao.update(grupo);
        } else
        grupoDao.save(grupo);
    }

    public List<Grupo> listarTodos() {
        return grupoDao.listar(Grupo.class);
    }



    public void excluir(Grupo grupo) {
        grupoDao.delete(grupo,  grupo.getId());
    }

    public Grupo busca(Long id) {
        return grupoDao.findById(id);
    }



}
