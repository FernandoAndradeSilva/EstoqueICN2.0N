package br.com.estoque.dao;


import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;

import javax.persistence.TypedQuery;
import java.util.List;

public class ClasseDao extends AbstractDao<Classe, Long> {


    public List<Classe> listarPorGrupo(Grupo grupo) {

        List<Classe> lista = manager.createQuery
                ("Select c from " + Classe.class.getName() + " c  where c.grupo = " + grupo.getId()).getResultList();

        return lista;

    }



}
