package br.com.estoque.model;

import javax.persistence.Entity;

@Entity(name = "unidadeMedida")
public class UnidadeDeMedida extends AbstractEntity {

    private String sigla;
    private String nome;


    @Override
    public String toString() {
        return sigla + " - " + nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
