package br.com.estoque.service;


import br.com.estoque.dao.UsuarioDao;
import br.com.estoque.model.Usuario;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class UsuarioService implements Serializable  , AbstractService <Usuario, Long> {

    @Inject
    private UsuarioDao usuarioDao;


    @Override
    public void salvar(Usuario entidade) {
        if(entidade.getId() >0) {
            usuarioDao.update(entidade);
        } else
            usuarioDao.save(entidade);
    }

    @Override
    public Usuario salvaRetorna(Usuario entidade) {
        return usuarioDao.saveReturn(entidade);
    }

    @Override
    public void editar(Usuario entidade) {

    }

    @Override
    public void excluir(Usuario entidade) {
        usuarioDao.delete(entidade ,  entidade.getId());
    }

    @Override
    public Usuario buscar(Usuario entidade) {
        return null;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioDao.findById(id);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioDao.listar(Usuario.class);
    }
}
