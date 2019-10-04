package br.com.estoque.dao;


import br.com.estoque.model.Categoria;
import br.com.estoque.model.Tipo;

import java.util.List;

public class TipoDao extends AbstractDao<Tipo, Long> {


    public List<Tipo> listarPorCategoria(Categoria categoria) {

        List<Tipo> lista = manager.createQuery("Select e from " + Tipo.class.getName() + " e " +
                "where e.categoria_id = :param").setParameter("param" , categoria.getId()).getResultList();
        return lista;

    }



}
