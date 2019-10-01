package br.com.estoque.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDao<E, PK> implements Serializable {

    private final Class<E> entityClass =
            (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public void save(E entidade) {

        manager.persist(entidade);
    }

    public void update(E entidade) {

        manager.merge(entidade);
    }

    public E saveReturn(E entidade) {

        return manager.merge(entidade);
    }

    public E findById(PK id) {

        return manager.find(entityClass, id);
    }

    public List<E> listar(Class<E> entidade) {

        List<E> lista = manager.createQuery("Select e from " + entidade.getName() + " e").getResultList();
        return lista;

    }

    public void delete(E entidade , PK id) {



        manager.remove(this.findById(id));
    }
}




