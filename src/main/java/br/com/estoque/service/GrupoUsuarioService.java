package br.com.estoque.service;


import br.com.estoque.dao.ClasseDao;
import br.com.estoque.dao.GrupoUsuarioDao;
import br.com.estoque.model.Classe;
import br.com.estoque.model.Grupo;
import br.com.estoque.model.GrupoUsuario;
import br.com.estoque.model.Usuario;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class GrupoUsuarioService implements Serializable {

    @Inject
    private GrupoUsuarioDao grupoUsuarioDao;

    public void salvar(GrupoUsuario grupoUsuario) {
            grupoUsuarioDao.save(grupoUsuario);
    }

    public List<GrupoUsuario> listarTodas() {
        return grupoUsuarioDao.listar(GrupoUsuario.class);
    }

    public List<Grupo> gruposNaoAssociados(Long id) {
        return grupoUsuarioDao.gruposNaoAssociados(id);
    }

    public List<Grupo> gruposAssociados(Long id) {
        return grupoUsuarioDao.gruposAssociados(id);
    }

//    public List<GrupoUsuario> listaPorUsuario(Long id) {
//        return grupoUsuarioDao.listaPorUsuario(id);
//    }

}
