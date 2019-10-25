package br.com.estoque.dao;

import br.com.estoque.model.*;

import java.util.List;

public class EstoqueDao extends AbstractDao<Estoque, EstoquePK> {


    public List<Estoque> listarPorUnidade(Unidade unidade) {

        List<Estoque> lista = manager.createQuery("Select e from " + Estoque.class.getName() + " e " +
                " where e.id.unidade = " + unidade.getId()).getResultList();

        return lista;
    }
}
