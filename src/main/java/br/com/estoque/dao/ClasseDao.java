package br.com.estoque.dao;


import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;

import java.util.List;

public class ClasseDao extends AbstractDao<Classe, Long> {


    public List<Classe> listarPorGrupo(Grupo grupo) {

        List<Classe> lista = manager.createQuery
                ("Select c.nome from " + Classe.class.getName() + " " +
                        "c where c.grupo = " + grupo.getId()).getResultList();
        System.out.println(lista);

        return lista;

    }



}
