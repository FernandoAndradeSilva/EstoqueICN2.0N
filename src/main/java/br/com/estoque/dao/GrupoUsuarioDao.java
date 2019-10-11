package br.com.estoque.dao;

import br.com.estoque.model.Grupo;
import br.com.estoque.model.GrupoUsuario;
import br.com.estoque.model.Usuario;

import java.util.List;

public class GrupoUsuarioDao extends AbstractDao<GrupoUsuario, Long> {


    public List<Grupo> gruposNaoAssociados(Long id) {
        List<Grupo> lista = manager.createQuery("Select e from " + Grupo.class.getName() + " e " +
                " where e not in(:parameter)").setParameter("parameter" , this.gruposAssociados(id)).getResultList();
        return lista;
    }


    public List<Grupo> gruposAssociados(Long id) {
        List<Grupo> lista = manager.createQuery("Select e.id.grupo from " + GrupoUsuario.class.getName() + " e " +
                " where e.id.usuario = " + id).getResultList();
        return lista;

    }


}
