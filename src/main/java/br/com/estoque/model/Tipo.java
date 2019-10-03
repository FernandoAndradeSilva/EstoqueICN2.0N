package br.com.estoque.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tipo")
public class Tipo extends AbstractEntity {


    private String nome;


    private String sigla;

    @ManyToOne
    @JoinColumn(name="categoria_id",  nullable= false)
    private Categoria categoria = new Categoria();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return nome + " - " + sigla;
    }
}
