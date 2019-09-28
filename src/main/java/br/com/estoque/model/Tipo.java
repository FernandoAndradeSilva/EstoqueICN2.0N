package br.com.estoque.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tipo")
public class Tipo extends AbstractEntity {

    private String nome;

    @ManyToOne
    @JoinColumn(name="categoria_id",  nullable= false)
    private Categoria categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }




}
