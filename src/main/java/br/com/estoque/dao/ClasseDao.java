package br.com.estoque.dao;


import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;

import java.util.List;

public class ClasseDao extends AbstractDao<Classe, Long> {


    public List<Classe> listarPorCategoria(Grupo grupo) {

        List<Classe> lista = manager.createQuery("Select e from " + Classe.class.getName() + " e " +
                "where e.categoria_id = :param").setParameter("param" , grupo.getId()).getResultList();
        return lista;

    }



}
