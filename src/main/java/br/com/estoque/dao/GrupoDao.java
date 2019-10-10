package br.com.estoque.dao;

import br.com.estoque.model.Grupo;
import br.com.estoque.model.Usuario;

import java.util.List;

public class GrupoDao extends AbstractDao<Grupo, Long> {


    public List<Grupo> listarGruposNaoSelecionados(Usuario usuario) {

        List<Grupo> gruposDoUsuario = manager.createQuery("Select e from " + entidade.getName() + " e").getResultList();
        return lista;

    }



}
