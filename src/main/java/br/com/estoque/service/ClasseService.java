package br.com.estoque.service;

import br.com.estoque.dao.ClasseDao;
import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;
import br.com.estoque.util.Transacional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClasseService implements Serializable , AbstractService<Classe , Long> {

    @Inject
    private ClasseDao classeDao;

    @Override
    public void salvar(Classe entidade) {
        if(entidade.getId() >0) {
            classeDao.update(entidade);
        } else
            classeDao.save(entidade);
    }

    @Override
    public Classe salvaRetorna(Classe entidade) {
        return classeDao.saveReturn(entidade);
    }

    @Override
    public void editar(Classe entidade) {

    }

    @Override
    public void excluir(Classe entidade) {
        classeDao.delete(entidade,  entidade.getId());
    }

    @Override
    public Classe buscar(Classe entidade) {
        return classeDao.findById(entidade.getId());
    }

    @Override
    public Classe buscarPorId(Long id) {
        return classeDao.findById(id);
    }

    @Override
    public List<Classe> listar() {
        return null;
    }

    public List<Classe> listarPorGrupo(Grupo grupo) {
        if(grupo == null || grupo.getId() == 0) {
            return new ArrayList<Classe>();
        } else
            return classeDao.listarPorGrupo(grupo);
    }

}
