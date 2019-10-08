package br.com.estoque.service;


import br.com.estoque.dao.UsuarioDao;
import br.com.estoque.model.Usuario;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class UsuarioService implements Serializable {

    @Inject
    private UsuarioDao usuarioDao;

    public void salvar(Usuario usuario) {

        if(usuario.getId() >0) {
            usuarioDao.update(usuario);
        } else
            usuarioDao.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioDao.listar(Usuario.class);
    }

    public void excluir(Usuario usuario) {
        usuarioDao.delete(usuario ,  usuario.getId());
    }

    public Usuario salvaRetorna(Usuario usuario) {
        return usuarioDao.saveReturn(usuario);
    }
}
