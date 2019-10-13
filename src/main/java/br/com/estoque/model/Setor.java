package br.com.estoque.model;

import javax.persistence.*;

@Entity(name = "setor")
public class Setor extends AbstractEntity {


    private String nome;
    private String andar;
    private String gestor;

    @ManyToOne
    @JoinColumn(name = "unidade", nullable = false)
    private Unidade unidade = new Unidade();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }


    @Override
    public String toString() {
        return nome;
    }


}
