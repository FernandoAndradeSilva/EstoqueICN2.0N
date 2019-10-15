package br.com.estoque.service;


import br.com.estoque.dao.EstoqueDao;
import br.com.estoque.model.EstoquePK;
import br.com.estoque.model.Grupo;
import br.com.estoque.model.Estoque;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class EstoqueService implements Serializable  {

    @Inject
    private EstoqueDao estoqueDao;

    public void salvar(Estoque estoque) {
            estoqueDao.save(estoque);
    }

    public void excluir(Estoque estoque) {
        estoqueDao.delete(estoque , estoque.getId());
    }

    public List<Estoque> listarTodas() {
        return estoqueDao.listar(Estoque.class);
    }

    public Estoque buscar(Estoque estoque) {
        return estoqueDao.findById(estoque.getId());
    }




}
