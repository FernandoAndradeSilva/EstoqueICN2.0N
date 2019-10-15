package br.com.estoque.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "classe")
public class Classe extends AbstractEntity {


    private String nome;


    private String sigla;

    @ManyToOne
    @JoinColumn(name="grupo",  nullable= false)
    private Grupo grupo = new Grupo();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return sigla + " - " + nome;
    }
}
