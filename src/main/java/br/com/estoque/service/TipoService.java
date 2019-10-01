package br.com.estoque.service;


import br.com.estoque.dao.TipoDao;
import br.com.estoque.model.Tipo;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class TipoService implements Serializable {

    @Inject
    private TipoDao tipoDao;

    public void salvar(Tipo tipo) {

        if(tipo.getId() >0) {
            tipoDao.update(tipo);
        } else
            tipoDao.save(tipo);
    }

    public List<Tipo> listarTodas() {
        return tipoDao.listar(Tipo.class);
    }

    public void excluir(Tipo tipo) {
        tipoDao.delete(tipo ,  tipo.getId());
    }

    public Tipo salvaRetorna(Tipo tipo) {
        return tipoDao.saveReturn(tipo);
    }
}
