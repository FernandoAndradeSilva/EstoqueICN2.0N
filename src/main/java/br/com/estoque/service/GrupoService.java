package br.com.estoque.service;
import br.com.estoque.dao.GrupoDao;
import br.com.estoque.model.Grupo;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class GrupoService implements Serializable , AbstractService<Grupo , Long> {


    @Inject
    private GrupoDao grupoDao;

    @Override
    public void salvar(Grupo entidade) {
        if(entidade.getId() >0) {
            grupoDao.update(entidade);
        } else
            grupoDao.save(entidade);
    }

    @Override
    public Grupo salvaRetorna(Grupo entidade) {
        return grupoDao.saveReturn(entidade);
    }

    @Override
    public void editar(Grupo entidade) {

    }

    @Override
    public void excluir(Grupo entidade) {
        grupoDao.delete(entidade,  entidade.getId());
    }

    @Override
    public Grupo buscar(Grupo entidade) {
        return grupoDao.findById(entidade.getId());
    }

    @Override
    public Grupo buscarPorId(Long id) {
        return grupoDao.findById(id);
    }

    @Override
    public List<Grupo> listar() {
        return grupoDao.listar(Grupo.class);
    }


}
