package br.com.estoque.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "grupo")
public class Grupo extends AbstractEntity {

    private String nome;
    private String sigla;
    private String descricao;

    @OneToMany(mappedBy="grupo" , fetch = FetchType.EAGER)
    private List<Classe> classes = new ArrayList<>();

    @OneToMany(mappedBy = "id.usuario" , fetch = FetchType.LAZY)
    private List<GrupoUsuario> usuarios = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<GrupoUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<GrupoUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return nome + " - " + sigla;
    }
}
