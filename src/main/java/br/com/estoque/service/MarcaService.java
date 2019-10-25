package br.com.estoque.service;

import br.com.estoque.dao.MarcaDao;
import br.com.estoque.model.Marca;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class MarcaService implements Serializable , AbstractService<Marca, Long> {


    @Inject
    private MarcaDao grupoDao;

    @Override
    public void salvar(Marca entidade) {
        if(entidade.getId() >0) {
            grupoDao.update(entidade);
        } else
            grupoDao.save(entidade);
    }

    @Override
    public Marca salvaRetorna(Marca entidade) {
        return grupoDao.saveReturn(entidade);
    }

    @Override
    public void editar(Marca entidade) {

    }

    @Override
    public void excluir(Marca entidade) {
        grupoDao.delete(entidade,  entidade.getId());
    }

    @Override
    public Marca buscar(Marca entidade) {
        return grupoDao.findById(entidade.getId());
    }

    @Override
    public Marca buscarPorId(Long id) {
        return grupoDao.findById(id);
    }

    @Override
    public List<Marca> listar() {
        return grupoDao.listar(Marca.class);
    }


}
