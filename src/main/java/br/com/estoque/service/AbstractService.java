package br.com.estoque.service;

import java.util.List;

public interface AbstractService <E , PK> {


    void salvar(E entidade);

    E salvaRetorna(E entidade);

    void editar(E entidade);

    void excluir(E entidade);

    E buscar(E entidade);

    E buscarPorId(PK id);

    List<E> listar();

}
