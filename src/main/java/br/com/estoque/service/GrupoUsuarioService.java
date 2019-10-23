package br.com.estoque.service;

import br.com.estoque.dao.GrupoUsuarioDao;
import br.com.estoque.model.Grupo;
import br.com.estoque.model.GrupoUsuario;
import br.com.estoque.model.GrupoUsuarioPK;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class GrupoUsuarioService implements Serializable, AbstractService <GrupoUsuario , GrupoUsuarioPK> {

    @Inject
    private GrupoUsuarioDao grupoUsuarioDao;


    @Override
    public void salvar(GrupoUsuario entidade) {
        grupoUsuarioDao.save(entidade);
    }

    @Override
    public GrupoUsuario salvaRetorna(GrupoUsuario entidade) {
        return null;
    }

    @Override
    public void editar(GrupoUsuario entidade) {

    }

    @Override
    public void excluir(GrupoUsuario entidade) {
        grupoUsuarioDao.delete(entidade , entidade.getId());
    }

    @Override
    public GrupoUsuario buscar(GrupoUsuario entidade) {
        return null;
    }

    @Override
    public GrupoUsuario buscarPorId(GrupoUsuarioPK id) {
        return null;
    }

    @Override
    public List<GrupoUsuario> listar() {
        return grupoUsuarioDao.listar(GrupoUsuario.class);
    }

    public List<Grupo> gruposNaoAssociados(Long id) {
        return grupoUsuarioDao.gruposNaoAssociados(id);
    }

    public List<Grupo> gruposAssociados(Long id) {
        return grupoUsuarioDao.gruposAssociados(id);
    }

}
