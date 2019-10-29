package br.com.estoque.service;

import br.com.estoque.dao.FornecedorDao;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.security.LogonMB;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class FornecedorService implements Serializable , AbstractService<Fornecedor , Long> {


    @Inject
    private FornecedorDao fornecedorDao;

    @Override
    public void salvar(Fornecedor entidade) {
        if(entidade.getId() >0) {
            fornecedorDao.update(entidade);
        } else

            entidade.setUsuario(LogonMB.usuarioDaSessao());
            fornecedorDao.save(entidade);
    }

    @Override
    public Fornecedor salvaRetorna(Fornecedor entidade) {
        return fornecedorDao.saveReturn(entidade);
    }

    @Override
    public void editar(Fornecedor entidade) {

    }

    @Override
    public void excluir(Fornecedor entidade) {
        fornecedorDao.delete(entidade,  entidade.getId());
    }

    @Override
    public Fornecedor buscar(Fornecedor entidade) {
        return fornecedorDao.findById(entidade.getId());
    }

    @Override
    public Fornecedor buscarPorId(Long id) {
        return fornecedorDao.findById(id);
    }

    @Override
    public List<Fornecedor> listar() {
        return fornecedorDao.listar(Fornecedor.class);
    }


}
