package br.com.estoque.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "categoria")
public class Categoria extends AbstractEntity {

    private String nome;

    @OneToMany(mappedBy="categoria" , fetch = FetchType.EAGER)
    private List<Tipo> tipos = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }



    @Override
    public String toString() {
        return nome;
    }



}
