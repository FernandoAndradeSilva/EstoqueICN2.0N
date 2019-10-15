package br.com.estoque.service;

import br.com.estoque.dao.AbstractDao;
import br.com.estoque.util.Transacional;

import javax.inject.Inject;
import java.io.Serializable;

public class GenericService <E> implements Serializable{

    @Inject
    private AbstractDao abstractDao;

    @Transacional
    public void saveTeste(E entidade) {
        abstractDao.save(entidade);
    }





}
