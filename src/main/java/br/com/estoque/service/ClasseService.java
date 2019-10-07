package br.com.estoque.service;


import br.com.estoque.dao.ClasseDao;
import br.com.estoque.model.Grupo;
import br.com.estoque.model.Classe;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class ClasseService implements Serializable {

    @Inject
    private ClasseDao classeDao;

    public void salvar(Classe classe) {

        if(classe.getId() >0) {
            classeDao.update(classe);
        } else
            classeDao.save(classe);
    }

    public List<Classe> listarTodas() {
        return classeDao.listar(Classe.class);
    }

    public void excluir(Classe classe) {
        classeDao.delete(classe,  classe.getId());
    }



    public Classe salvaRetorna(Classe classe) {
        return classeDao.saveReturn(classe);
    }

    public List<Classe> listarPorCategoria(Grupo grupo) {

        return classeDao.listarPorCategoria(grupo);
    }
}
