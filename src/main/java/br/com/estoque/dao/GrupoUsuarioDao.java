package br.com.estoque.dao;

import br.com.estoque.model.Grupo;
import br.com.estoque.model.GrupoUsuario;
import br.com.estoque.model.GrupoUsuarioPK;
import br.com.estoque.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class GrupoUsuarioDao extends AbstractDao<GrupoUsuario, GrupoUsuarioPK> {


    public List<Grupo> gruposNaoAssociados(Long id) {

        List<Grupo> gruposAssociados = this.gruposAssociados(id);
        if(gruposAssociados.size() !=0) {
            List<Grupo> lista = manager.createQuery("Select e from " + Grupo.class.getName() + " e " +
                    " where e not in(:parameter)").setParameter("parameter" , gruposAssociados).getResultList();
            return lista;
        } else
            return manager.createQuery("Select e from " + Grupo.class.getName() + " e ").getResultList();
    }


    public List<Grupo> gruposAssociados(Long id) {
        List<Grupo> lista = manager.createQuery("Select e.id.grupo from " + GrupoUsuario.class.getName() + " e " +
                " where e.id.usuario = " + id).getResultList();
        return lista;

    }

    public void excluirGrupoUsuario(GrupoUsuario grupoUsuario) {

        manager.createQuery("delete e from " + GrupoUsuario.class.getName() + " e where e.id.usuario = 1l" );


    }

}
